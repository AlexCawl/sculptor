package org.alexcawl.sculptor.common.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.reflect.KClass

typealias ResolveRenderer = (layoutClass: KClass<out Layout>) -> Renderer<Layout>

@Immutable
data class RendererScope @InternalSculptorApi constructor(
    @PublishedApi
    internal val resolveRenderer: ResolveRenderer,
) {
    @Composable
    fun render(layout: Layout) = resolveRenderer(layout::class).internalRender(scope = this, layout = layout)
}
