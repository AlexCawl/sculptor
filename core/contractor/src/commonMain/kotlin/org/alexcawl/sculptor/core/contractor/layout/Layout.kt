package org.alexcawl.sculptor.core.contractor.layout

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier

/**
 * Represents a layout configuration for a UI component.
 *
 * @property id A unique identifier for the layout.
 * @property modifier A [Modifier] that can be used to adjust the layout's behavior and appearance.
 * @property uiState The current state of the UI component, encapsulated in a [UiState] object.
 */
@Immutable
public data class Layout(
    public val id: String,
    public val modifier: Modifier,
    public val uiState: UiState,
)
