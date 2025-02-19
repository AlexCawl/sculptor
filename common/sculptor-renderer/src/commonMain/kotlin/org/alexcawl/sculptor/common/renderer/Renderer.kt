@file:Suppress("UNCHECKED_CAST", "FunctionName")

package org.alexcawl.sculptor.common.renderer

import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public abstract class Renderer<L : Layout> {
    public abstract val layout: KClass<L>

    @InternalSculptorApi
    @Composable
    public fun internalDraw(scope: RendererScope, layout: Layout): Unit = scope.Draw(layout as L)

    @InternalSculptorApi
    public fun internalMeasure(scope: RendererScope, layout: Layout): Boolean = scope.Measure(layout as L)

    /**
     * TODO: docs
     */
    @Composable
    public abstract fun RendererScope.Draw(layout: L)

    /**
     * TODO: docs
     */
    public abstract fun RendererScope.Measure(layout: L): Boolean
}
