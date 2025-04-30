package org.alexcawl.sculptor.internal.mvi.logging

import org.alexcawl.sculptor.internal.mvi.core.Next
import org.alexcawl.sculptor.internal.mvi.core.Update

internal class LoggingUpdate<State : Any, Event : Any, Command : Any, News : Any>(
    private val delegate: Update<State, Event, Command, News>,
    private val logger: UpdateLogger,
) : Update<State, Event, Command, News> {
    override suspend fun reduce(state: State, event: Event): Next<State, Command, News> {
        val next: Next<State, Command, News> = delegate.reduce(state, event)

        logger.log(tag = ACTION_TAG, message = event.toString())

        if (next.state != null) {
            logger.log(tag = STATE_TAG, message = next.state.toString())
        }

        if (next.commands.isNotEmpty()) {
            for (command in next.commands) {
                logger.log(COMMAND_TAG, command.toString())
            }
        }

        if (next.news.isNotEmpty()) {
            for (news in next.news) {
                logger.log(tag = NEWS_TAG, message = news.toString())
            }
        }

        return next
    }

    private companion object {
        const val ACTION_TAG = "ACTION"
        const val STATE_TAG = "STATE"
        const val COMMAND_TAG = "COMMAND"
        const val NEWS_TAG = "NEWS"
    }
}
