package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.Renderer
import org.alexcawl.sculptor.common.layout.RendererScope
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public typealias SculptorScreen = @Composable () -> Unit

/**
 * TODO: docs
 */
public sealed interface SculptorRenderer {
    /**
     * TODO: docs
     */
    public fun render(layout: Layout): SculptorScreen

    /**
     * TODO: docs
     */
    public fun findRenderer(layoutClass: KClass<out Layout>): Renderer<Layout>

    /**
     * TODO: docs
     */
    public operator fun plus(other: SculptorRenderer): SculptorRenderer

    /**
     * TODO: docs
     */
    public interface State {
        /**
         * TODO: docs
         */
        public val renderers: List<Renderer<Layout>>
    }

    /**
     * TODO: docs
     */
    public companion object Factory {
        /**
         * TODO: docs
         */
        public fun create(state: State): SculptorRenderer =
            SculptorRendererImpl(renderers = state.renderers)
    }
}

@OptIn(InternalSculptorApi::class)
private class SculptorRendererImpl(
    private val renderers: List<Renderer<Layout>>,
) : SculptorRenderer {
    override fun render(layout: Layout): SculptorScreen = {
        val rendererScope = RendererScope(resolveRenderer = this::findRenderer)
        val renderer: Renderer<Layout> = findRenderer(layout::class)
        renderer.internalRender(scope = rendererScope, layout = layout)
    }

    override fun findRenderer(layoutClass: KClass<out Layout>): Renderer<Layout> =
        renderers.find { it.layout == layoutClass } ?: error("No renderer found for $layoutClass")

    override fun plus(other: SculptorRenderer): SculptorRenderer = when (other) {
        is SculptorRendererImpl -> SculptorRendererImpl(renderers = renderers + other.renderers)
    }
}
