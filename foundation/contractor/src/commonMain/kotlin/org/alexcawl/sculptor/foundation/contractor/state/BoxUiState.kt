package org.alexcawl.sculptor.foundation.contractor.state

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import org.alexcawl.sculptor.core.contractor.layout.Layout
import org.alexcawl.sculptor.core.contractor.layout.UiState

@Immutable
internal data class BoxUiState(
    val contentAlignment: Alignment,
    val propagateMinConstraints: Boolean,
    val content: List<Layout>,
) : UiState
