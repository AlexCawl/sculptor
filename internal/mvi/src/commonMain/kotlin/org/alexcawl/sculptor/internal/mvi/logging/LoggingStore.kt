package org.alexcawl.sculptor.internal.mvi.logging

import org.alexcawl.sculptor.internal.mvi.core.UseCase
import org.alexcawl.sculptor.internal.mvi.core.Reducer
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.internal.mvi.logging.impl.LoggingReducerImpl
import org.alexcawl.sculptor.internal.mvi.logging.impl.LoggingUseCaseImpl

/**
 * Creates a [Store] instance with logging capabilities.
 *
 * This function initializes a [Store] with the provided initial state, commands, use cases, and reducers.
 * It also accepts a [StoreLogger] to log the initial state and commands, as well as any subsequent actions
 * performed by the use cases and reducers.
 *
 * @param initialState The initial state of the store.
 * @param initialCommands A list of initial commands to be processed by the store.
 * @param useCases A list of use cases that the store will use to handle commands and produce events.
 * @param reducers A list of reducers that the store will use to update the state based on events.
 * @param logger A [StoreLogger] instance to log the store's actions. Defaults to [StoreLogger.NoOp] if not provided.
 * @return A new [Store] instance with logging enabled.
 */
public fun <State : Any, Event : Any, Command : Any> Store.Companion.create(
    initialState: State,
    initialCommands: List<Command> = emptyList(),
    useCases: List<UseCase<Command, Event>> = emptyList(),
    reducers: List<Reducer<State, Event, Command>>,
    logger: StoreLogger = StoreLogger.NoOp,
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
            LoggingUseCaseImpl(
                delegate = useCase,
                logger = logger,
            )
        },
        reducers = reducers.map { reducer: Reducer<State, Event, Command> ->
            LoggingReducerImpl(
                delegate = reducer,
                logger = logger,
            )
        },
    )
}

internal const val INITIAL_STATE_TAG = "STORE_INITIAL_STATE"
internal const val INITIAL_COMMAND_TAG = "STORE_INITIAL_COMMAND"
