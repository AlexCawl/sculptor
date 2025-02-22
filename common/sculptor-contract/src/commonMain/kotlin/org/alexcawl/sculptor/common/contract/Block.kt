package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A contract for a layout block. Represents a single `@Composable` function in most cases.
 */
@Serializable
public data class Block<SC : StateContract>(
    /**
     * The unique identifier of the layout.
     */
    @SerialName("id")
    public val id: Identifier,

    /**
     * The current state of the layout.
     */
    @SerialName("state")
    public val state: Identifier,

    /**
     * The common set of modifiers that all states inherit from.
     */
    @SerialName("modifiers")
    public val modifiers: List<ModifierContract>,

    /**
     * The set of states that make up the layout. There must be at least one state.
     */
    @SerialName("states")
    public val states: List<SC>,
)
