package org.alexcawl.sculptor.runtime.engine.mvi

import org.alexcawl.sculptor.internal.mvi.core.Reducer
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.internal.mvi.core.UseCase
import org.alexcawl.sculptor.internal.mvi.logging.create
import org.alexcawl.sculptor.runtime.engine.dependencies.logger.Logger

internal class SculptorStore(
    initialState: SculptorState = SculptorState.Loading,
    initialCommands: List<SculptorCommand> = emptyList(),
    useCases: List<UseCase<SculptorCommand, SculptorEvent>> = emptyList(),
    reducers: List<Reducer<SculptorState, SculptorEvent, SculptorCommand>> = emptyList(),
    logger: SculptorUpdateLogger = SculptorUpdateLogger(logger = Logger.NoOp),
) : Store<SculptorState, SculptorEvent> by Store.create(
    initialState = initialState,
    initialCommands = initialCommands,
    useCases = useCases,
    reducers = reducers,
    logger = logger,
)
