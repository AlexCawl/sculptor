package org.alexcawl.sculptor.engine.impl

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererProvider
import org.alexcawl.sculptor.engine.SculptorInteractor
import org.alexcawl.sculptor.engine.SculptorState

internal class SculptorInteractorImpl(
    private val scope: CoroutineScope,
    private val rendererProvider: RendererProvider,
) : SculptorInteractor  {
    private val internalState: MutableStateFlow<SculptorState> = MutableStateFlow(SculptorState.Loading)

    override val state: StateFlow<SculptorState> = internalState.asStateFlow()

    override suspend fun handle(deeplink: String) {

    }

    override fun renderer(layout: Layout): Renderer<UiState> {
        return rendererProvider.findRenderer(layout.uiState::class)
    }
}
