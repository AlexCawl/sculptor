package org.alexcawl.sculptor.common.renderer

import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public inline fun <reified L : Layout> renderer(
    layoutType: KClass<L>,
    crossinline drawer: @Composable RendererScope.(layout: L) -> Unit,
    crossinline measurer: RendererScope.(layout: L) -> Boolean,
): Renderer<L> = object : Renderer<L>() {
    override val layout: KClass<L> = layoutType

    @Composable
    override fun RendererScope.Draw(layout: L) = drawer(layout)

    override fun RendererScope.Measure(layout: L): Boolean = measurer(layout)
}
