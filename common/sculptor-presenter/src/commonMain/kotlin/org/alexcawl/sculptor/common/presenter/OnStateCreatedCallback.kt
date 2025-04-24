package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.layout.UiState

public fun interface OnStateCreatedCallback {
    public suspend operator fun invoke(uiState: UiState)
}
