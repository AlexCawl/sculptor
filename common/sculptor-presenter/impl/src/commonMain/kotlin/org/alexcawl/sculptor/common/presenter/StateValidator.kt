package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.layout.UiState

public fun interface StateValidator {
    public suspend fun canBeDrawn(uiState: UiState): Boolean
}
