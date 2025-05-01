package org.alexcawl.sculptor.runtime.engine.dependencies.validation

import org.alexcawl.sculptor.core.layout.UiState

public interface ExternalStateValidator {
    public suspend fun validate(uiState: UiState): Boolean
}
