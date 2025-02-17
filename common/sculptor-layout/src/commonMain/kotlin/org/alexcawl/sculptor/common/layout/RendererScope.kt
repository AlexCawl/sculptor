package org.alexcawl.sculptor.common.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.core.InternalSculptorApi
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
    public fun render(layout: Layout): Unit = resolveRenderer(layout::class)
        .internalRender(scope = this, layout = layout)
}
