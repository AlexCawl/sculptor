package org.alexcawl.sculptor.runtime.engine.domain

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.core.layout.Layout

@Immutable
internal sealed interface SculptorState {
    @Immutable
    data object Loading : SculptorState

    @Immutable
    data class Idle(val layout: Layout) : SculptorState

    @Immutable
    data object Error : SculptorState
}
