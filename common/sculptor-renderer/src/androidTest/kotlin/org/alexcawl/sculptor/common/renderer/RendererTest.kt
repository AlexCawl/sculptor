package org.alexcawl.sculptor.common.renderer

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.junit.Test
import org.junit.rules.TestRule

abstract class RendererTest<L : Layout> {
    @OptIn(InternalSculptorApi::class)
    protected open val rendererScope: RendererScope
        get() = RendererScope(
            resolveRenderer = { _ -> error("Mock") },
        )

    abstract val renderer: Renderer<L>
    abstract val input: L

    @Test
    fun rendererTest() {

    }
}
