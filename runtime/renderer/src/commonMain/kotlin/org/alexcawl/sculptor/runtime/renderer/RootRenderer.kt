package org.alexcawl.sculptor.runtime.renderer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.core.layout.Layout

public interface RootRenderer {
    @Stable
    @Composable
    public fun Draw(layout: Layout)
}
