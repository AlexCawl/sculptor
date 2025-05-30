package org.alexcawl.sculptor.runtime.contractor.renderer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.core.contractor.layout.Layout

public interface RootRenderer {
    @Stable
    @Composable
    public fun Draw(layout: Layout)
}
