package org.alexcawl.sculptor.engine.impl

import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.presenter.OnStateCreatedCallback
import org.alexcawl.sculptor.common.renderer.ResolveRenderer

internal class OnStateCreatedCallbackImpl(
    private val resolveRenderer: ResolveRenderer,
) : OnStateCreatedCallback {
    override suspend fun invoke(uiState: UiState) {
        resolveRenderer(uiState::class)
    }
}
