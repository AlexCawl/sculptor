package org.alexcawl.sculptor.foundation.contractor.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import org.alexcawl.sculptor.core.contractor.layout.Layout
import org.alexcawl.sculptor.core.contractor.layout.UiState

@Immutable
internal data class ColumnUiState(
    val verticalArrangement: Arrangement.Vertical,
    val horizontalAlignment: Alignment.Horizontal,
    val content: List<Layout>,
) : UiState
