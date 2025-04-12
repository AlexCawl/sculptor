package org.alexcawl.sculptor.common.renderer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.common.layout.Layout

@Stable
public interface RendererScope {
    @Stable
    @Composable
    public fun draw(layout: Layout)

    public companion object {
        public operator fun invoke(resolveRenderer: ResolveRenderer): RendererScope =
            RendererScopeImpl(resolveRenderer = resolveRenderer)
    }
}

@Stable
private data class RendererScopeImpl(
    private val resolveRenderer: ResolveRenderer,
): RendererScope {
    @Stable
    @Composable
    override fun draw(layout: Layout) = resolveRenderer(
        uiStateClass = layout.uiState::class
    ).Draw(
        scope = this,
        id = layout.id,
        modifier = layout.modifier,
        state = layout.uiState,
    )
}
