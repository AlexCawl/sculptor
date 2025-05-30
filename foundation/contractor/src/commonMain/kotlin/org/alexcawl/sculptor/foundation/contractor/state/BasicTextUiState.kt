package org.alexcawl.sculptor.foundation.contractor.state

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import org.alexcawl.sculptor.core.contractor.layout.UiState

@Immutable
internal data class BasicTextUiState(
    val text: String,
    val softWrap: Boolean,
    val maxLines: Int,
    val minLines: Int,
    val textStyle: TextStyle,
) : UiState
