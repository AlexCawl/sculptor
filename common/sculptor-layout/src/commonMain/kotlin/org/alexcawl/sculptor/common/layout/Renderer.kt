@file:Suppress("UNCHECKED_CAST")

package org.alexcawl.sculptor.common.layout

import androidx.compose.runtime.Composable
import kotlin.reflect.KClass

abstract class Renderer<L : Layout> {
    abstract val layout: KClass<L>

    @Composable
    fun internalRender(scope: RendererScope, layout: Layout) = scope.Render(layout as L)

    @Composable
    abstract fun RendererScope.Render(layout: L)
}
