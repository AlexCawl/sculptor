package org.alexcawl.sculptor.runtime.renderer.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.core.renderer.RendererScope
import org.alexcawl.sculptor.runtime.renderer.RendererProvider

@Stable
public data class RendererScopeImpl(
    private val rendererProvider: RendererProvider,
) : RendererScope {
    @Stable
    @Composable
    override fun draw(layout: Layout) {
        rendererProvider
            .findRenderer(uiStateClass = layout.uiState::class)
            .Draw(scope = this, id = layout.id, modifier = layout.modifier, state = layout.uiState)
    }
}
