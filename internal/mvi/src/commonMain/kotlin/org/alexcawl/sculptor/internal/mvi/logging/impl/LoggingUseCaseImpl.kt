package org.alexcawl.sculptor.internal.mvi.logging.impl

import org.alexcawl.sculptor.internal.mvi.core.UseCase
import org.alexcawl.sculptor.internal.mvi.logging.StoreLogger
import kotlin.reflect.KClass

internal class LoggingUseCaseImpl<Command : Any, Event : Any>(
    private val delegate: UseCase<Command, Event>,
    private val logger: StoreLogger,
): UseCase<Command, Event> {
    override val type: KClass<Command> = delegate.type

    override suspend fun handle(command: Command): List<Event> {
        logger.log(COMMAND_RECEIVED_TAG, command.toString())
        val events: List<Event> = delegate.handle(command)

        if (events.isNotEmpty()) {
            for (event in events) {
                logger.log(EVENT_SENT_TAG, event.toString())
            }
        }

        return events
    }

    internal companion object {
        const val COMMAND_RECEIVED_TAG = "STORE_COMMAND_RECEIVED"
        const val EVENT_SENT_TAG = "STORE_EVENT_SENT"
    }
}
