package org.alexcawl.sculptor.foundation.renderer

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererScope
import org.alexcawl.sculptor.foundation.layout.ColumnLayout
import kotlin.reflect.KClass

@Stable
public class ColumnRenderer : Renderer<ColumnLayout>() {
    override val layout: KClass<ColumnLayout> = ColumnLayout::class

    @Composable
    override fun RendererScope.Draw(layout: ColumnLayout) {
        Column(
            modifier = layout.modifier,
            verticalArrangement = layout.verticalArrangement,
            horizontalAlignment = layout.horizontalAlignment,
            content = {
                layout.content.forEach { layout: Layout ->
                    draw(layout)
                }
            }
        )
    }

    override fun RendererScope.Measure(layout: ColumnLayout): Boolean = layout.content.all(::measure)
}
