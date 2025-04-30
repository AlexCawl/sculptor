package org.alexcawl.sculptor.internal.mvi.logging

import org.alexcawl.sculptor.internal.mvi.core.CommandHandler
import org.alexcawl.sculptor.internal.mvi.core.Next
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.internal.mvi.core.Update

public fun <State : Any, Event : Any, Command : Any, News : Any> Store.Companion.create(
    initialState: State,
    initialCommands: List<Command> = emptyList(),
    commandHandlers: List<CommandHandler<Command, Event>> = emptyList(),
    update: Update<State, Event, Command, News> = Update { _, _ -> Next() },
    logger: UpdateLogger = UpdateLogger { _, _ -> },
) : Store<State, Event, News> {
    logger.log(tag = INITIAL_STATE_TAG, message = initialState.toString())

    if (initialCommands.isNotEmpty()) {
        for (command in initialCommands) {
            logger.log(tag = INITIAL_COMMAND_TAG, message = command.toString())
        }
    }

    return create(
        initialState = initialState,
        initialCommands = initialCommands,
        commandHandlers = commandHandlers,
        update = LoggingUpdate(
            delegate = update,
            logger = logger,
        ),
    )
}

private const val INITIAL_STATE_TAG = "INITIAL_STATE"
private const val INITIAL_COMMAND_TAG = "INITIAL_COMMAND"
