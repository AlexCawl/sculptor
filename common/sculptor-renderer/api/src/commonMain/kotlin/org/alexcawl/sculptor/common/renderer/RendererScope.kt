package org.alexcawl.sculptor.common.renderer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.common.layout.Layout

@Stable
public interface RendererScope {
    @Stable
    @Composable
    public fun draw(layout: Layout)
}
