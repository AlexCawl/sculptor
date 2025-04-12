package org.alexcawl.sculptor.common.renderer

import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.common.layout.UiState
import kotlin.reflect.KClass

@Stable
public fun interface ResolveRenderer {
    public operator fun invoke(uiStateClass: KClass<out UiState>): Renderer<UiState>

    public companion object {
        public operator fun invoke(renderers: List<Renderer<UiState>>): ResolveRenderer =
            ResolveRenderer { uiStateClass: KClass<out UiState> ->
                renderers.find { it.state == uiStateClass }
                    ?: error(message = "Cannot resolve presenter for state $uiStateClass")
            }
    }
}
