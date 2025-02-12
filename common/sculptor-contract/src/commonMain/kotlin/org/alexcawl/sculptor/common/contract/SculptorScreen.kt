package org.alexcawl.sculptor.common.contract

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
    val values: List<ValueContract>,

    /**
     * The list of layouts.
     */
    val layout: List<LayoutContract<*>>,
)
