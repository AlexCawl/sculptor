package org.alexcawl.sculptor.internal.mvi.core.impl

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
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
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.alexcawl.sculptor.internal.mvi.core.Next
import org.alexcawl.sculptor.internal.mvi.core.Reducer
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.internal.mvi.core.UseCase
import kotlin.reflect.safeCast

internal class StoreImpl<out State : Any, in Event : Any, Command : Any>(
    initialState: State,
    private val initialCommands: List<Command> = emptyList(),
    private val useCases: List<UseCase<Command, Event>> = emptyList(),
    private val reducers: List<Reducer<State, Event, Command>>,
) : Store<State, Event> {
    private val stateFlow: MutableStateFlow<State> = MutableStateFlow(value = initialState)
    private val eventChannel: Channel<Event> = Channel(capacity = Channel.UNLIMITED)
    private val commandChannel: Channel<Command> = Channel(capacity = Channel.UNLIMITED)

    override fun launchIn(coroutineScope: CoroutineScope) {
        val sharedCommands: Flow<Command> = commandChannel
            .consumeAsFlow()
            .shareIn(coroutineScope, SharingStarted.Eagerly)

        val commandsFlow: Flow<Command> = if (initialCommands.isNotEmpty()) {
            merge(sharedCommands, initialCommands.asFlow())
        } else {
            sharedCommands
        }

        for (useCase: UseCase<Command, Event> in useCases) {
            coroutineScope.launch(start = CoroutineStart.UNDISPATCHED) {
                subscribeOnCommandFlow(useCase = useCase, commandsFlow = commandsFlow)
                    .catch { throwable: Throwable ->
                        when (throwable) {
                            is CancellationException -> throw throwable
                            else -> error("Error from ${useCase::class}: $throwable")
                        }
                    }.collect(collector = eventChannel::send)
            }
        }

        coroutineScope.launch {
            while (isActive) {
                val event: Event = eventChannel.receive()
                val reducer: Reducer<State, Event, Command> = findReducer(event = event)
                val next: Next<State, Command> = reducer.reduce(state.value, event)
                if (next.state != null) {
                    stateFlow.value = next.state
                }
                for (command: Command in next.commands) {
                    commandChannel.send(element = command)
                }
            }
        }
    }

    override val state: StateFlow<State> = stateFlow.asStateFlow()

    override fun dispatch(event: Event) {
        eventChannel.trySend(event)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun subscribeOnCommandFlow(
        useCase: UseCase<Command, Event>,
        commandsFlow: Flow<Command>,
    ): Flow<Event> {
        return commandsFlow
            .mapNotNull(transform = useCase.type::safeCast)
            .flatMapConcat { command: Command ->
                useCase.handle(command = command).asFlow()
            }
    }

    private fun findReducer(event: Event): Reducer<State, Event, Command> {
        return reducers.firstOrNull { reducer: Reducer<State, Event, Command> ->
            reducer.key.isInstance(event)
        } ?: error("No reducer for event: $event")
    }
}