package org.alexcawl.sculptor.common.renderer

import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.common.layout.UiState
import kotlin.jvm.Throws
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
