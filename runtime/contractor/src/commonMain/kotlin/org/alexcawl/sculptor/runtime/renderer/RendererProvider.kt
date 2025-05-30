package org.alexcawl.sculptor.runtime.renderer

import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.renderer.Renderer
import kotlin.reflect.KClass

/**
 * Functional interface for providing a renderer based on the UI state class.
 * This interface is used to find and return a renderer that can handle a specific UI state.
 *
 * @see UiState
 * @see Renderer
 */
@Stable
public fun interface RendererProvider {
    /**
     * Finds and returns a renderer for the given UI state class.
     *
     * @param uiStateClass the class of the UI state for which a renderer is needed
     * @return a renderer that can handle the given UI state class
     * @throws IllegalStateException if no renderer is found for the given UI state class
     */
    @Throws(IllegalStateException::class)
    public fun findRenderer(uiStateClass: KClass<out UiState>): Renderer<UiState>
}
