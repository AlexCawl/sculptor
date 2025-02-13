package org.alexcawl.sculptor.common.layout

import androidx.compose.runtime.Composable
import kotlin.reflect.KClass

inline fun <reified L : Layout> renderer(
    layoutType: KClass<L>,
    crossinline renderer: @Composable RendererScope.(layout: L) -> Unit
): Renderer<L> = object : Renderer<L>() {
    override val layout: KClass<L> = layoutType

    @Composable
    override fun RendererScope.Render(layout: L) = renderer(layout)
}
