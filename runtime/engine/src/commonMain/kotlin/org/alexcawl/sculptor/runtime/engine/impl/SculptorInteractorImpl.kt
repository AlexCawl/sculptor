package org.alexcawl.sculptor.runtime.engine.impl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.renderer.Renderer
import org.alexcawl.sculptor.runtime.engine.SculptorInteractor
import org.alexcawl.sculptor.runtime.engine.SculptorState
import org.alexcawl.sculptor.runtime.renderer.RendererProvider

internal class SculptorInteractorImpl(
    private val scope: CoroutineScope,
    private val rendererProvider: RendererProvider,
) : SculptorInteractor {
    private val internalState: MutableStateFlow<SculptorState> = MutableStateFlow(SculptorState.Loading)

    override val state: StateFlow<SculptorState> = internalState.asStateFlow()

    override suspend fun handle(deeplink: String) {

    }

    override fun renderer(layout: Layout): Renderer<UiState> {
        return rendererProvider.findRenderer(layout.uiState::class)
    }
}
