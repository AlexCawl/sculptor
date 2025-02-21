package org.alexcawl.sculptor.common.renderer.test

import androidx.compose.foundation.text.BasicText
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererTest
import org.alexcawl.sculptor.common.renderer.mock.MockLayout
import org.alexcawl.sculptor.common.renderer.renderer

class MockLayoutRendererTest : RendererTest<MockLayout>() {
    override val renderer: Renderer<MockLayout> = renderer(
        layoutType = MockLayout::class,
        drawer = { layout: MockLayout ->
            BasicText(text = layout.value)
        },
        measurer = { _: MockLayout -> true }
    )

}