package org.alexcawl.sculptor.foundation.layout

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import org.alexcawl.sculptor.core.layout.UiState

@Immutable
public data class BasicTextUiState(
    val text: String,
    val softWrap: Boolean,
    val maxLines: Int,
    val minLines: Int,
    val textStyle: TextStyle,
) : UiState
