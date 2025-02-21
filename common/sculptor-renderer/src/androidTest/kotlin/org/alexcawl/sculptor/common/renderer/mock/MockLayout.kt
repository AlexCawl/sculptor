package org.alexcawl.sculptor.common.renderer.mock

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.layout.Layout

@Immutable
data class MockLayout(
    override val id: String,
    override val modifier: Modifier,
    val value: String,
) : Layout
