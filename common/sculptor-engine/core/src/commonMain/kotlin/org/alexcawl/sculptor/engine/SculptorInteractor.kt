package org.alexcawl.sculptor.engine

import kotlinx.coroutines.flow.StateFlow
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.renderer.Renderer

internal interface SculptorInteractor {
    suspend fun handle(deeplink: String)

    fun renderer(layout: Layout): Renderer<UiState>

    val state: StateFlow<SculptorState>
}
