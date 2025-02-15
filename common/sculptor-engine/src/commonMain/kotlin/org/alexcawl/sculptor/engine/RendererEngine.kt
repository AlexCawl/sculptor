package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.Renderer
import org.alexcawl.sculptor.common.layout.RendererState
import org.alexcawl.sculptor.common.layout.RendererScope
import kotlin.reflect.KClass

typealias SculptorScreen = @Composable () -> Unit

interface RendererEngine {
    fun render(layout: Layout): SculptorScreen

    fun findRenderer(layoutClass: KClass<out Layout>): Renderer<Layout>

    companion object Factory {
        fun create(state: RendererState): RendererEngine =
            RendererEngineImpl(renderers = state.renderers)
    }
}

@OptIn(InternalSculptorApi::class)
private class RendererEngineImpl(
    private val renderers: List<Renderer<Layout>>,
) : RendererEngine {
    override fun render(layout: Layout): SculptorScreen = {
        val rendererScope = RendererScope(resolveRenderer = this::findRenderer)
        val renderer: Renderer<Layout> = findRenderer(layout::class)
        renderer.internalRender(scope = rendererScope, layout = layout)
    }

    override fun findRenderer(layoutClass: KClass<out Layout>): Renderer<Layout> =
        renderers.find { it.layout == layoutClass } ?: error("No renderer found for $layoutClass")
}
