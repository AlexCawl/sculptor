package org.alexcawl.sculptor.runtime.engine.domain.reducers

import org.alexcawl.sculptor.internal.mvi.core.ReducerDsl
import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState

internal abstract class SculptorReducer<Event : SculptorEvent> : ReducerDsl<SculptorState, Event, SculptorCommand>()
