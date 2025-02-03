package org.alexcawl.sculptor.common.layout

import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.core.InternalSculptorApi

abstract class Renderer<L : Layout> {
    @Composable
    @InternalSculptorApi
    fun render(scope: RendererScope, layout: L) = scope.Render(layout)

    @Composable
    abstract fun RendererScope.Render(layout: L)
}