package org.alexcawl.sculptor.common.layout

import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

abstract class Renderer<L : Layout> {
    abstract val layout: KClass<L>

    @Composable
    @InternalSculptorApi
    @Suppress("UNCHECKED_CAST")
    fun render(scope: RendererScope, layout: Layout) = scope.Render(layout as L)

    @Composable
    abstract fun RendererScope.Render(layout: L)
}
