package org.alexcawl.sculptor.common.renderer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.layout.UiState
import kotlin.reflect.KClass

/**
 * Abstract class for rendering UI states.
 *
 * This class represents a generic template for creating renderers that can display
 * various UI states. Each concrete renderer must implement the [state] property and the [Draw]
 * function to define how a specific state should be rendered.
 *
 * @param S the type of state, which must be a subclass of [UiState].
 */
@Stable
public abstract class Renderer<S : UiState> {
    /**
     * Abstract property returning the class of the state used by this renderer.
     *
     * @return the class of the state that implements the [UiState] interface.
     */
    public abstract val state: KClass<S>

    /**
     * Abstract function for rendering the UI state.
     *
     * This function must be implemented in subclasses to define the rendering logic.
     *
     * @param scope an object that provides additional context for rendering.
     * @param id a unique identifier for the component.
     * @param modifier a modifier that can change the appearance and behavior of the component.
     * @param state the current state of the component used for rendering.
     */
    @Stable
    @Composable
    public abstract fun Draw(scope: RendererScope, id: String, modifier: Modifier, state: S)
}
