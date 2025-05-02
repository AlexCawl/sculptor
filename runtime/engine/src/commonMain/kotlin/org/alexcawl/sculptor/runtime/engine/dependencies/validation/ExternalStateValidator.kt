package org.alexcawl.sculptor.runtime.engine.dependencies.validation

import org.alexcawl.sculptor.core.layout.UiState

internal interface ExternalStateValidator {
    suspend fun validate(uiState: UiState): Boolean
}
