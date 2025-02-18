package org.alexcawl.sculptor.foundation.renderer

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererScope
import org.alexcawl.sculptor.foundation.layout.BoxLayout
import kotlin.reflect.KClass

@Stable
public class BoxRenderer : Renderer<BoxLayout>() {
    override val layout: KClass<BoxLayout> = BoxLayout::class

    @Composable
    override fun RendererScope.Render(layout: BoxLayout) {
        Box(
            modifier = layout.modifier,
            contentAlignment = layout.contentAlignment,
            propagateMinConstraints = layout.propagateMinConstraints,
            content = {
                layout.content.forEach { layout: Layout ->
                    render(layout)
                }
            }
        )
    }
}
