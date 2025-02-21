package org.alexcawl.sculptor.common.renderer.test

import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererTest
import org.alexcawl.sculptor.common.renderer.mock.MockLayout
import org.alexcawl.sculptor.common.renderer.renderer
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MockLayoutRendererTest : RendererTest<MockLayout>() {
    override val renderer: Renderer<MockLayout> = renderer(
        layoutType = MockLayout::class,
        drawer = { layout: MockLayout ->
            BasicText(
                text = layout.value,
                modifier = layout.modifier.testTag(tag = layout.id),
            )
        },
        measurer = { _: MockLayout -> true }
    )
    override val input: MockLayout = MockLayout(
        id = "mockedText",
        modifier = Modifier,
        value = "value",
    )

    @OptIn(ExperimentalTestApi::class)
    override fun ComposeUiTest.assert() {
        val textNode = onNodeWithTag(testTag = "mockedText")
        textNode.assertIsDisplayed()
        textNode.assertTextEquals("value")
    }
}
