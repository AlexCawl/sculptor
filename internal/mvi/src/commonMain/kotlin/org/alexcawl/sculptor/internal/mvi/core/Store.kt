package org.alexcawl.sculptor.internal.mvi.core

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

public interface Store<out State, in Event, out News> {
    public val state: StateFlow<State>

    public val news: Flow<News>

    public fun dispatch(event: Event)

    public fun launchIn(coroutineScope: CoroutineScope)

    public companion object {
        public fun <State : Any, Event : Any, Command : Any, News : Any> create(
            initialState: State,
            initialCommands: List<Command> = emptyList(),
            commandHandlers: List<CommandHandler<Command, Event>> = emptyList(),
            update: Update<State, Event, Command, News> = Update { _, _ -> Next() },
        ): Store<State, Event, News> {
            return StoreImpl(
                initialState = initialState,
                initialCommands = initialCommands,
                commandHandlers = commandHandlers,
                update = update,
            )
        }
    }
}

private class StoreImpl<State : Any, Event : Any, Command : Any, News : Any>(
    initialState: State,
    private val initialCommands: List<Command> = emptyList(),
    private val commandHandlers: List<CommandHandler<Command, Event>>,
    private val update: Update<State, Event, Command, News>,
) : Store<State, Event, News> {
    private val stateFlow: MutableStateFlow<State> = MutableStateFlow(value = initialState)
    private val eventChannel: Channel<Event> = Channel(capacity = Channel.UNLIMITED)
    private val commandChannel: Channel<Command> = Channel(capacity = Channel.UNLIMITED)
    private val newsChannel: Channel<News> = Channel(capacity = Channel.UNLIMITED)

    private val lock: Any = Any()
    private var isLocked: Boolean = false

    override fun launchIn(coroutineScope: CoroutineScope) {
        synchronized(lock = lock) {
            if (isLocked) {
                error("Store has already been launched")
            } else {
                isLocked = true
            }

            val sharedCommands: Flow<Command> = commandChannel
                .consumeAsFlow()
                .shareIn(coroutineScope, SharingStarted.Eagerly)

            val commandsFlow = if (initialCommands.isNotEmpty()) {
                merge(sharedCommands, initialCommands.asFlow())
            } else {
                sharedCommands
            }

            for (flowHandler in commandHandlers) {
                coroutineScope.launch(start = CoroutineStart.UNDISPATCHED) {
                    flowHandler.handle(commandsFlow)
                        .catch { throwable ->
                            if (throwable is CancellationException) throw throwable
                            else error("Error from ${flowHandler::class}: $throwable")
                        }
                        .collect(eventChannel::send)
                }
            }

            coroutineScope.launch {
                while (isActive) {
                    val event = eventChannel.receive()
                    val next = update.reduce(state.value, event)
                    if (next.state != null) {
                        stateFlow.value = next.state
                    }
                    for (command in next.commands) {
                        commandChannel.send(command)
                    }
                    for (news in next.news) {
                        newsChannel.send(news)
                    }
                }
            }
        }
    }

    override val state: StateFlow<State> = stateFlow.asStateFlow()

    @OptIn(DelicateCoroutinesApi::class)
    override val news: Flow<News> = newsChannel
        .receiveAsFlow()
        .shareIn(scope = GlobalScope, started = SharingStarted.WhileSubscribed())

    override fun dispatch(event: Event) {
        eventChannel.trySend(event)
    }
}
