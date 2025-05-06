package org.alexcawl.sculptor.core.layout

import androidx.compose.runtime.Stable

/**
 * Represents the state of the user interface.
 *
 * This interface is marked with the [Stable] annotation to indicate that instances of implementing classes
 * can be safely used in Compose's remember mechanism without causing unnecessary recompositions.
 */
@Stable
public interface UiState
