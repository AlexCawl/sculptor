package org.alexcawl.sculptor.runtime.renderer.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.core.renderer.RendererScope
import org.alexcawl.sculptor.runtime.renderer.RendererProvider
import org.alexcawl.sculptor.runtime.renderer.RootRenderer

public class RootRendererImpl(
    private val rendererProvider: RendererProvider,
    private val rendererScope: RendererScope,
) : RootRenderer {
    @Stable
    @Composable
    override fun Draw(layout: Layout) {
        rendererProvider
            .findRenderer(uiStateClass = layout.uiState::class)
            .Draw(scope = rendererScope, id = layout.id, modifier = layout.modifier, state = layout.uiState)
    }
}
