package org.alexcawl.sculptor.common.renderer

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.runComposeUiTest
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.junit.Test

abstract class RendererTest<L : Layout> {
    @OptIn(InternalSculptorApi::class)
    protected open val rendererScope: RendererScope
        get() = RendererScope(
            resolveRenderer = { _ -> error("Mock") },
        )

    private val rule: ComposeContentTestRule = createComposeRule()

    abstract val renderer: Renderer<L>
    abstract val input: L

    @OptIn(ExperimentalTestApi::class)
    abstract fun ComposeUiTest.assert()

    @OptIn(InternalSculptorApi::class, ExperimentalTestApi::class)
    @Test
    fun rendererTest() = runComposeUiTest {
        setContent {
            renderer.internalDraw(scope = rendererScope, layout = input)
        }
        assert()
    }
}
