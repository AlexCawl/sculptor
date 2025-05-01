package org.alexcawl.sculptor.internal.mvi.logging

import org.alexcawl.sculptor.internal.mvi.core.UseCase
import org.alexcawl.sculptor.internal.mvi.core.Reducer
import org.alexcawl.sculptor.internal.mvi.core.Store

public fun <State : Any, Event : Any, Command : Any> Store.Companion.create(
    initialState: State,
    initialCommands: List<Command> = emptyList(),
    useCases: List<UseCase<Command, Event>> = emptyList(),
    reducers: List<Reducer<State, Event, Command>>,
    logger: UpdateLogger = UpdateLogger { _, _ -> },
): Store<State, Event> {
    logger.log(tag = INITIAL_STATE_TAG, message = initialState.toString())

    if (initialCommands.isNotEmpty()) {
        for (command in initialCommands) {
            logger.log(tag = INITIAL_COMMAND_TAG, message = command.toString())
        }
    }

    return create(
        initialState = initialState,
        initialCommands = initialCommands,
        useCases = useCases.map { useCase: UseCase<Command, Event> ->
            LoggingUseCase(
                delegate = useCase,
                logger = logger,
            )
        },
        reducers = reducers.map { reducer: Reducer<State, Event, Command> ->
            LoggingReducer(
                delegate = reducer,
                logger = logger,
            )
        },
    )
}

internal const val INITIAL_STATE_TAG = "INITIAL_STATE"
internal const val INITIAL_COMMAND_TAG = "INITIAL_COMMAND"
