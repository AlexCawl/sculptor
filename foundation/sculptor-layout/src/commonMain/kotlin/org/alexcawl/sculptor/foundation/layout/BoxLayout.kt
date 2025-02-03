package org.alexcawl.sculptor.foundation.layout

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.layout.Layout

@Immutable
data class BoxLayout(
    override val id: String,
    override val modifier: Modifier,
    val contentAlignment: Alignment,
    val propagateMinConstraints: Boolean,
    val content: List<Layout>,
) : Layout
