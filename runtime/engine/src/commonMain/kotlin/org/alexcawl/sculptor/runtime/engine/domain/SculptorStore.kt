package org.alexcawl.sculptor.runtime.engine.domain

import org.alexcawl.sculptor.internal.mvi.core.Reducer
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.internal.mvi.core.UseCase
import org.alexcawl.sculptor.internal.mvi.logging.create
import org.alexcawl.sculptor.runtime.engine.dependencies.logger.Logger
import org.alexcawl.sculptor.runtime.engine.ui.SculptorState

internal class SculptorStore(
    initialState: SculptorState = SculptorState.Loading,
    initialCommands: List<SculptorCommand> = emptyList(),
    useCases: List<UseCase<SculptorCommand, SculptorEvent>> = emptyList(),
    reducers: List<Reducer<SculptorState, SculptorEvent, SculptorCommand>> = emptyList(),
    logger: SculptorUpdateLogger = SculptorUpdateLogger(logger = Logger),
) : Store<SculptorState, SculptorEvent> by Store.create(
    initialState = initialState,
    initialCommands = initialCommands,
    useCases = useCases,
    reducers = reducers,
    logger = logger,
)
