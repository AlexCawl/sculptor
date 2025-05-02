package org.alexcawl.sculptor.runtime.engine.mvi

import org.alexcawl.sculptor.internal.mvi.core.ReducerDsl

internal abstract class SculptorReducer<Event : SculptorEvent> : ReducerDsl<SculptorState, Event, SculptorCommand>()
