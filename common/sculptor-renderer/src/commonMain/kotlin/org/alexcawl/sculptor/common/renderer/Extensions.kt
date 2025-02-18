package org.alexcawl.sculptor.common.renderer

import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public inline fun <reified L : Layout> renderer(
    layoutType: KClass<L>,
    crossinline renderer: @Composable RendererScope.(layout: L) -> Unit
): Renderer<L> = object : Renderer<L>() {
    override val layout: KClass<L> = layoutType

    @Composable
    override fun RendererScope.Render(layout: L) = renderer(layout)
}
