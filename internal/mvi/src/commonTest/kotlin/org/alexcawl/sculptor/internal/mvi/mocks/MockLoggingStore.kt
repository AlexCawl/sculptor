package org.alexcawl.sculptor.internal.mvi.mocks

import org.alexcawl.sculptor.internal.mvi.core.UseCase
import org.alexcawl.sculptor.internal.mvi.core.Reducer
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.internal.mvi.logging.UpdateLogger
import org.alexcawl.sculptor.internal.mvi.logging.create
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Command
import org.alexcawl.sculptor.internal.mvi.mocks.entity.Event
import org.alexcawl.sculptor.internal.mvi.mocks.entity.State

class MockLoggingStore(
    initialState: State = State.Initial,
    initialCommands: List<Command> = emptyList(),
    useCases: List<UseCase<Command, Event>> = emptyList(),
    reducers: List<Reducer<State, Event, Command>> = emptyList(),
    logger: UpdateLogger = UpdateLogger { _, _ -> }
) : Store<State, Event> by Store.create(
    initialState = initialState,
    initialCommands = initialCommands,
    useCases = useCases,
    reducers = reducers,
    logger = logger,
)
