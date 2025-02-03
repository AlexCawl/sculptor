package org.alexcawl.sculptor.foundation.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.layout.Layout

@Immutable
data class ColumnLayout(
    override val id: String,
    override val modifier: Modifier,
    val verticalArrangement: Arrangement.Vertical,
    val horizontalAlignment: Alignment.Horizontal,
    val content: List<Layout>,
) : Layout
