package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.value.ValueContract

/**
 * A `@Serializable` description for a single screen.
 */
@Serializable
data class SculptorScreen(
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
    val layout: List<LayoutContract>,
)
