package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.layout.Layout

@Immutable
internal sealed interface SculptorState {
    @Immutable
    data object Loading : SculptorState

    @Immutable
    data class Content(val layout: Layout) : SculptorState

    @Immutable
    data object Error : SculptorState
}
