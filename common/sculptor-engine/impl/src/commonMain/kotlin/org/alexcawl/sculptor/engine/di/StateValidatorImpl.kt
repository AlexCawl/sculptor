package org.alexcawl.sculptor.engine.di

import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.presenter.StateValidator
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererProvider

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
