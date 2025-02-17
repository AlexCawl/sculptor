package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName

/**
 * A contract for a state of layout.
 */
public interface StateContract {
    /**
     * The unique identifier of the state.
     */
    @SerialName("id")
    public val id: Identifier

    /**
     * The modifiers that are applied to the state. Inherited from the base modifiers.
     */
    @SerialName("modifiers")
    public val modifiers: List<ModifierContract>
}
