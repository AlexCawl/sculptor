package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A `@Serializable` description for a single screen.
 */
@Serializable
public data class Scaffold(
    /**
     * The identifier of the root layout.
     */
    @SerialName("root_layout_id")
    public val rootLayoutId: Identifier,

    /**
     * The list of values.
     */
    @SerialName("values")
    @Contextual
    public val values: List<ValueContract>,

    /**
     * The list of layouts.
     */
    @SerialName("layouts")
    @Contextual
    public val layouts: List<Block<StateContract>>,
)
