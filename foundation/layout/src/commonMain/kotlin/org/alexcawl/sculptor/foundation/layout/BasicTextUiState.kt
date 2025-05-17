package org.alexcawl.sculptor.foundation.layout

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.core.layout.UiState

@Immutable
public data class BasicTextUiState(
    val text: String,
    val softWrap: Boolean? = null,
    val maxLines: Int? = null,
    val minLines: Int? = null,
) : UiState
