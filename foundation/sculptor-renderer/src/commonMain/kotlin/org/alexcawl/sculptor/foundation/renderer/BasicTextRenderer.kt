package org.alexcawl.sculptor.foundation.renderer

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererScope
import org.alexcawl.sculptor.foundation.layout.BasicTextLayout
import kotlin.reflect.KClass

@Stable
public class BasicTextRenderer : Renderer<BasicTextLayout>() {
    override val layout: KClass<BasicTextLayout> = BasicTextLayout::class

    @Composable
    override fun RendererScope.Draw(layout: BasicTextLayout) {
        BasicText(
            modifier = layout.modifier,
            text = layout.text,
            softWrap = layout.softWrap,
            maxLines = layout.maxLines,
            minLines = layout.minLines,
        )
    }

    override fun RendererScope.Measure(layout: BasicTextLayout): Boolean = true
}
