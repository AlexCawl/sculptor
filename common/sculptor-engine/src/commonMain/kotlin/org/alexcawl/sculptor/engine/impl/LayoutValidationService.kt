package org.alexcawl.sculptor.engine.impl

import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.presenter.StateCreateCallback
import org.alexcawl.sculptor.common.renderer.ResolveRenderer

internal class LayoutValidationService(
    private val resolveRenderer: ResolveRenderer,
) : StateCreateCallback {
    override suspend fun invoke(uiState: UiState) {
        resolveRenderer(uiState::class)
    }
}