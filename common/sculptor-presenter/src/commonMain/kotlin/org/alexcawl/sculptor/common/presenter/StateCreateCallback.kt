package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.layout.UiState

public fun interface StateCreateCallback {
    public suspend operator fun invoke(uiState: UiState)
}
