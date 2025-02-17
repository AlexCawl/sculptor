package org.alexcawl.sculptor.foundation.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.layout.Layout

@Immutable
public data class RowLayout(
    override val id: String,
    override val modifier: Modifier,
    val horizontalArrangement: Arrangement.Horizontal,
    val verticalAlignment: Alignment.Vertical,
    val content: List<Layout>,
) : Layout
