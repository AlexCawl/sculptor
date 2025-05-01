package org.alexcawl.sculptor.runtime.presenter

import org.alexcawl.sculptor.core.layout.UiState

/**
 * Functional interface for validating the state of the user interface.
 * Implementations of this interface determine whether a given UI state can be drawn.
 */
public fun interface StateValidator {
    /**
     * Checks if the provided UI state can be drawn.
     *
     * @param uiState The current state of the user interface.
     * @return `true` if the UI state can be drawn, `false` otherwise.
     */
    public suspend fun canBeDrawn(uiState: UiState): Boolean
}
