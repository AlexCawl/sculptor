package org.alexcawl.sculptor.runtime.engine.dependencies.validation.impl

import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.renderer.Renderer
import org.alexcawl.sculptor.runtime.engine.dependencies.validation.ExternalStateValidator
import org.alexcawl.sculptor.runtime.presenter.StateValidator
import org.alexcawl.sculptor.runtime.renderer.RendererProvider

internal class StateValidatorImpl(
    private val rendererProvider: RendererProvider,
    private val externalStateValidator: ExternalStateValidator,
) : StateValidator {
    override suspend fun canBeDrawn(uiState: UiState): Boolean {
        val rendererForUiState: Result<Renderer<UiState>> = runCatching {
            rendererProvider.findRenderer(uiState::class)
        }
        return rendererForUiState.isSuccess && externalStateValidator.validate(uiState)
    }
}
