package org.alexcawl.sculptor.core.renderer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.core.layout.Layout

/**
 * Interface defining the scope for rendering operations in the application.
 * This interface is used to encapsulate the rendering logic and ensure stability
 * and optimization in the Jetpack Compose UI system.
 *
 * @see Layout
 */
@Stable
public interface RendererScope {
    /**
     * Draws the specified layout within the rendering scope.
     * This method is part of the composable UI and should be used to render
     * the provided layout description.
     *
     * @param layout The layout description to be rendered.
     * @see Layout
     */
    @Stable
    @Composable
    public fun draw(layout: Layout)
}
