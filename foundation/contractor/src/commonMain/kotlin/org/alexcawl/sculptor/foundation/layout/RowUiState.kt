package org.alexcawl.sculptor.foundation.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.core.layout.UiState

@Immutable
public data class RowUiState(
    val horizontalArrangement: Arrangement.Horizontal,
    val verticalAlignment: Alignment.Vertical,
    val content: List<Layout>,
) : UiState
