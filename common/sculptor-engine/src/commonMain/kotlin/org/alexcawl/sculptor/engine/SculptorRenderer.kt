package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererScope
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
    public val renderers: List<Renderer<Layout>>

    /**
     * TODO: docs
     */
    public fun measure(layout: Layout): Result<Boolean>

    /**
     * TODO: docs
     */
    public fun draw(layout: Layout): SculptorScreen

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
    override val renderers: List<Renderer<Layout>>,
) : SculptorRenderer {
    override fun measure(layout: Layout): Result<Boolean> = runCatching {
        val rendererScope = RendererScope(resolveRenderer = this::findRenderer)
        val renderer: Renderer<Layout> = findRenderer(layout::class)
        renderer.internalMeasure(rendererScope, layout)
    }

    override fun draw(layout: Layout): SculptorScreen = {
        val rendererScope = RendererScope(resolveRenderer = this::findRenderer)
        val renderer: Renderer<Layout> = findRenderer(layout::class)
        renderer.internalDraw(scope = rendererScope, layout = layout)
    }

    private fun findRenderer(layoutClass: KClass<out Layout>): Renderer<Layout> =
        renderers.find { it.layout == layoutClass } ?: error("No renderer found for $layoutClass")

    override fun plus(other: SculptorRenderer): SculptorRenderer = SculptorRendererImpl(
        renderers = renderers + other.renderers
    )
}
