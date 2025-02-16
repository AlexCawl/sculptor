package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * A `@Serializable` description for a single screen.
 */
@Serializable
data class Scaffold(
    /**
     * The identifier of the root layout.
     */
    val rootLayoutId: Identifier,

    /**
     * The list of values.
     */
    @Contextual
    val values: List<ValueContract>,

    /**
     * The list of layouts.
     */
    @Contextual
    val layouts: List<LayoutContract>,
)
