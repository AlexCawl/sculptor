package org.alexcawl.sculptor.common.contract.layout

import androidx.annotation.Size
import kotlinx.serialization.SerialName
import org.alexcawl.sculptor.common.contract.Contract
import org.alexcawl.sculptor.common.contract.Identifier

/**
 * A contract for a layout block. Represents a single `@Composable` function in most cases.
 */
interface LayoutContract : Contract {
    /**
     * The unique identifier of the layout.
     */
    @SerialName("id")
    val id: Identifier

    /**
     * The current state of the layout.
     */
    @SerialName("state")
    val state: Identifier

    /**
     * The common set of modifiers that all states inherit from.
     */
    @SerialName("modifiers")
    val modifiers: List<ModifierContract>

    /**
     * The set of states that make up the layout. There must be at least one state.
     */
    @get:Size(min = 1)
    @SerialName("states")
    val states: List<StateContract>
}
