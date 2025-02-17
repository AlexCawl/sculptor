package org.alexcawl.sculptor.foundation.layout.renderer

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.common.layout.Renderer
import org.alexcawl.sculptor.common.layout.RendererScope
import org.alexcawl.sculptor.foundation.layout.BasicTextLayout
import kotlin.reflect.KClass

@Stable
public class BasicTextRenderer : Renderer<BasicTextLayout>() {
    override val layout: KClass<BasicTextLayout> = BasicTextLayout::class

    @Composable
    override fun RendererScope.Render(layout: BasicTextLayout) {
        BasicText(
            modifier = layout.modifier,
            text = layout.text,
            softWrap = layout.softWrap,
            maxLines = layout.maxLines,
            minLines = layout.minLines,
        )
    }
}
