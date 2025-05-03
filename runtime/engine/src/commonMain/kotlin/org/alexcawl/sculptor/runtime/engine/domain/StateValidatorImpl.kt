package org.alexcawl.sculptor.runtime.engine.domain

import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.renderer.Renderer
import org.alexcawl.sculptor.runtime.presenter.StateValidator
import org.alexcawl.sculptor.runtime.renderer.RendererProvider

internal class StateValidatorImpl(
    private val rendererProvider: RendererProvider,
) : StateValidator {
    override suspend fun canBeDrawn(uiState: UiState): Boolean {
        val rendererForUiState: Result<Renderer<UiState>> = runCatching {
            rendererProvider.findRenderer(uiState::class)
        }
        return rendererForUiState.isSuccess
    }
}
