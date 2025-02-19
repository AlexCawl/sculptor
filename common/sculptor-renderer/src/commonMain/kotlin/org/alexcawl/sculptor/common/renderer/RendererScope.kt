package org.alexcawl.sculptor.common.renderer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public typealias ResolveRenderer = (layoutClass: KClass<out Layout>) -> Renderer<Layout>

/**
 * TODO: docs
 */
@Immutable
public data class RendererScope @InternalSculptorApi constructor(
    @PublishedApi
    internal val resolveRenderer: ResolveRenderer,
) {
    /**
     * TODO: docs
     */
    @OptIn(InternalSculptorApi::class)
    @Composable
    public fun draw(layout: Layout): Unit = resolveRenderer(layout::class)
        .internalDraw(scope = this, layout = layout)

    /**
     * TODO: docs
     */
    @OptIn(InternalSculptorApi::class)
    public fun measure(layout: Layout): Boolean = resolveRenderer(layout::class)
        .internalMeasure(scope = this, layout = layout)
}
