package org.alexcawl.sculptor.common.renderer

import androidx.compose.runtime.Stable
import org.alexcawl.sculptor.common.layout.UiState
import kotlin.reflect.KClass

@Stable
public fun interface RendererProvider {
    public fun findRenderer(uiStateClass: KClass<out UiState>): Renderer<UiState>
}
