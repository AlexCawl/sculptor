package org.alexcawl.sculptor.internal.mvi.logging.impl

import org.alexcawl.sculptor.internal.mvi.core.Next
import org.alexcawl.sculptor.internal.mvi.core.Reducer
import org.alexcawl.sculptor.internal.mvi.logging.StoreLogger
import kotlin.reflect.KClass

internal class LoggingReducerImpl<State : Any, Event : Any, Command : Any>(
    private val delegate: Reducer<State, Event, Command>,
    private val logger: StoreLogger,
) : Reducer<State, Event, Command> {
    override val key: KClass<Event> = delegate.key

    override suspend fun reduce(state: State, event: Event): Next<State, Command> {
        val next: Next<State, Command> = delegate.reduce(state, event)

        logger.log(tag = EVENT_RECEIVED_TAG, message = event.toString())

        if (next.state != state) {
            logger.log(tag = STATE_TAG, message = next.state.toString())
        }

        if (next.commands.isNotEmpty()) {
            for (command in next.commands) {
                logger.log(COMMAND_SENT_TAG, command.toString())
            }
        }

        return next
    }

    internal companion object {
        const val STATE_TAG = "STATE"
        const val EVENT_RECEIVED_TAG = "STORE_EVENT_RECEIVED"
        const val COMMAND_SENT_TAG = "STORE_COMMAND_SENT"
    }
}
