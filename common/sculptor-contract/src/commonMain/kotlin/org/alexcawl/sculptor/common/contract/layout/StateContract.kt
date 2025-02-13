package org.alexcawl.sculptor.common.contract.layout

import kotlinx.serialization.SerialName
import org.alexcawl.sculptor.common.contract.Identifier

/**
 * A contract for a state of layout.
 */
interface StateContract {
    /**
     * The unique identifier of the state.
     */
    @SerialName("id")
    val id: Identifier

    /**
     * The modifiers that are applied to the state. Inherited from the base modifiers.
     */
    @SerialName("modifiers")
    val modifiers: List<ModifierContract>
}
