package org.alexcawl.sculptor.common.layout

import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

typealias SculptorScreen = @Composable () -> Unit

sealed interface SculptorRenderer {
    fun render(layout: Layout): SculptorScreen

    fun findRenderer(layoutClass: KClass<out Layout>): Renderer<Layout>

    operator fun plus(other: SculptorRenderer): SculptorRenderer

    interface State {
        val renderers: List<Renderer<Layout>>
    }

    companion object Factory {
        fun create(state: State): SculptorRenderer =
            Impl(renderers = state.renderers)
    }
}

@OptIn(InternalSculptorApi::class)
private class Impl(private val renderers: List<Renderer<Layout>>) : SculptorRenderer {
    override fun render(layout: Layout): SculptorScreen = {
        val rendererScope = RendererScope(resolveRenderer = this::findRenderer)
        val renderer: Renderer<Layout> = findRenderer(layout::class)
        renderer.internalRender(scope = rendererScope, layout = layout)
    }

    override fun findRenderer(layoutClass: KClass<out Layout>): Renderer<Layout> =
        renderers.find { it.layout == layoutClass } ?: error("No renderer found for $layoutClass")

    override fun plus(other: SculptorRenderer): SculptorRenderer = when (other) {
        is Impl -> Impl(renderers = renderers + other.renderers)
    }
}
