package org.alexcawl.sculptor.foundation.layout.renderer

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.Renderer
import org.alexcawl.sculptor.common.layout.RendererScope
import org.alexcawl.sculptor.foundation.layout.BoxLayout

class BoxRenderer : Renderer<BoxLayout>() {
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
