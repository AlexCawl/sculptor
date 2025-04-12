package org.alexcawl.sculptor.common.layout

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier

@Immutable
public data class Layout(
    public val id: String,
    public val modifier: Modifier,
    public val uiState: UiState,
)
