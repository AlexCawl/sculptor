package org.alexcawl.sculptor.runtime.engine.domain

import org.alexcawl.sculptor.core.contractor.layout.UiState
import org.alexcawl.sculptor.core.contractor.renderer.Renderer
import org.alexcawl.sculptor.runtime.contractor.presenter.StateValidator
import org.alexcawl.sculptor.runtime.contractor.renderer.RendererProvider

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
