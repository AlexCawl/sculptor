@file:Suppress("UNCHECKED_CAST")

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
    public fun internalRender(scope: RendererScope, layout: Layout): Unit = scope.Render(layout as L)

    /**
     * TODO: docs
     */
    @Composable
    public abstract fun RendererScope.Render(layout: L)
}
