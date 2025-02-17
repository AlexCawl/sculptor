package org.alexcawl.sculptor.foundation.layout

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.layout.Layout

@Immutable
public data class BasicTextLayout(
    override val id: String,
    override val modifier: Modifier,
    val text: String,
    val softWrap: Boolean,
    val maxLines: Int,
    val minLines: Int,
) : Layout
