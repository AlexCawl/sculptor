package org.alexcawl.sculptor.runtime.engine

import kotlinx.coroutines.flow.StateFlow
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.renderer.Renderer

internal interface SculptorInteractor {
    suspend fun handle(deeplink: String)

    fun renderer(layout: Layout): Renderer<UiState>

    val state: StateFlow<SculptorState>
}
