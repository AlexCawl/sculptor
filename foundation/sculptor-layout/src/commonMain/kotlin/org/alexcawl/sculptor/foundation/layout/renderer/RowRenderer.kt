package org.alexcawl.sculptor.foundation.layout.renderer

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.Renderer
import org.alexcawl.sculptor.common.layout.RendererScope
import org.alexcawl.sculptor.foundation.layout.RowLayout
import kotlin.reflect.KClass

@Stable
public class RowRenderer : Renderer<RowLayout>() {
    override val layout: KClass<RowLayout> = RowLayout::class

    @Composable
    override fun RendererScope.Render(layout: RowLayout) {
        Row(
            modifier = layout.modifier,
            horizontalArrangement = layout.horizontalArrangement,
            verticalAlignment = layout.verticalAlignment,
            content = {
                layout.content.forEach { layout: Layout ->
                    render(layout)
                }
            }
        )
    }
}
