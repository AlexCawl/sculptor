package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName

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
