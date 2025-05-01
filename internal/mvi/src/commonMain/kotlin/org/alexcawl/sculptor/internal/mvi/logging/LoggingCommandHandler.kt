package org.alexcawl.sculptor.internal.mvi.logging

import org.alexcawl.sculptor.internal.mvi.core.CommandHandler
import kotlin.reflect.KClass

internal class LoggingCommandHandler<Command : Any, Event : Any>(
    private val delegate: CommandHandler<Command, Event>,
    private val logger: UpdateLogger,
): CommandHandler<Command, Event> {
    override val key: KClass<Command> = delegate.key

    override fun handle(command: Command): List<Event> {
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
        const val COMMAND_RECEIVED_TAG = "COMMAND_RECEIVED"
        const val EVENT_SENT_TAG = "EVENT_SENT"
    }
}