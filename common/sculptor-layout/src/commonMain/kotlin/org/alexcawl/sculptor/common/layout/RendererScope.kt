package org.alexcawl.sculptor.common.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

internal typealias DelegateRender = @Composable (layout: Layout) -> Unit

@Stable
abstract class RendererScope(
    @PublishedApi
    internal val delegateRender: DelegateRender
) {
    @Composable
    @Suppress("NOTHING_TO_INLINE")
    inline fun render(layout: Layout) = delegateRender(layout)
}