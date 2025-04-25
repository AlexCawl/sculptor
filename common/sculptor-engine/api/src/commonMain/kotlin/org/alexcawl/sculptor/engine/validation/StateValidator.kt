package org.alexcawl.sculptor.engine.validation

import org.alexcawl.sculptor.common.layout.UiState

public interface StateValidator {
    public suspend fun validate(uiState: UiState): Boolean
}
