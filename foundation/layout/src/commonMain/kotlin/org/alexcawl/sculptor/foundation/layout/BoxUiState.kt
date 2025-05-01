package org.alexcawl.sculptor.foundation.layout

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.core.layout.UiState

@Immutable
public data class BoxUiState(
    val contentAlignment: Alignment,
    val propagateMinConstraints: Boolean,
    val content: List<Layout>,
) : UiState
