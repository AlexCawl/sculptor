package org.alexcawl.sculptor.common.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.common.core.InternalSculptorApi

typealias DelegateRender = @Composable (layout: Layout) -> Unit

@Stable
class RendererScope @InternalSculptorApi constructor(
    @PublishedApi
    internal val delegateRender: DelegateRender
) {
    @Composable
    @Suppress("NOTHING_TO_INLINE")
    inline fun render(layout: Layout) = delegateRender(layout)
}
