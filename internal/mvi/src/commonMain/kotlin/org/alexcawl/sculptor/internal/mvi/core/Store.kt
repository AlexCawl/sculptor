package org.alexcawl.sculptor.internal.mvi.core

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.reflect.safeCast

public interface Store<out State : Any, in Event : Any> {
    public val state: StateFlow<State>

    public fun dispatch(event: Event)

    public fun launchIn(coroutineScope: CoroutineScope)

    public companion object {
        public fun <State : Any, Event : Any, Command : Any> create(
            initialState: State,
            initialCommands: List<Command> = emptyList(),
            commandHandlers: List<CommandHandler<Command, Event>> = emptyList(),
            reducers: List<Reducer<State, Event, Command>> = emptyList(),
        ): Store<State, Event> {
            return StoreImpl(
                initialState = initialState,
                initialCommands = initialCommands,
                commandHandlers = commandHandlers,
                reducers = reducers,
            )
        }
    }
}

private class StoreImpl<out State : Any, in Event : Any, Command : Any>(
    initialState: State,
    private val initialCommands: List<Command> = emptyList(),
    private val commandHandlers: List<CommandHandler<Command, Event>> = emptyList(),
    private val reducers: List<Reducer<State, Event, Command>>,
) : Store<State, Event> {
    private val stateFlow: MutableStateFlow<State> = MutableStateFlow(value = initialState)
    private val eventChannel: Channel<Event> = Channel(capacity = Channel.UNLIMITED)
    private val commandChannel: Channel<Command> = Channel(capacity = Channel.UNLIMITED)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun launchIn(coroutineScope: CoroutineScope) {
        val sharedCommands: Flow<Any> = commandChannel
            .consumeAsFlow()
            .shareIn(coroutineScope, SharingStarted.Eagerly)

        val commandsFlow = if (initialCommands.isNotEmpty()) {
            merge(sharedCommands, initialCommands.asFlow())
        } else {
            sharedCommands
        }

        for (flowHandler in commandHandlers) {
            coroutineScope.launch {
                commandsFlow
                    .filterIsInstance(klass = flowHandler.key)
                    .mapNotNull(transform = flowHandler.key::safeCast)
                    .flatMapConcat(transform = { flowHandler.handle(it).asFlow() })
                    .catch { throwable: Throwable ->
                        when (throwable) {
                            is CancellationException -> throw throwable
                            else -> error("Error from ${flowHandler::class}: $throwable")
                        }
                    }
                    .collect(eventChannel::send)
            }
        }

        coroutineScope.launch {
            while (isActive) {
                val event: Event = eventChannel.receive()
                val reducer: Reducer<State, Event, Command> = reducers
                    .firstOrNull { it.key.isInstance(event) }
                    .also { if (it == null) error("No reducer for event: $event") }
                    ?: continue
                val next: Next<State, Command> = reducer.reduce(state.value, event)
                if (next.state != null) {
                    stateFlow.value = next.state
                }
                for (command in next.commands) {
                    commandChannel.send(element = command)
                }
            }
        }
    }

    override val state: StateFlow<State> = stateFlow.asStateFlow()

    override fun dispatch(event: Event) {
        eventChannel.trySend(event)
    }
}
