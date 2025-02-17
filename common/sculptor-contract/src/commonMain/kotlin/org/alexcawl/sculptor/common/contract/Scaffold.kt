package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * A `@Serializable` description for a single screen.
 */
@Serializable
public data class Scaffold(
    /**
     * The identifier of the root layout.
     */
    public val rootLayoutId: Identifier,

    /**
     * The list of values.
     */
    @Contextual
    public val values: List<ValueContract>,

    /**
     * The list of layouts.
     */
    @Contextual
    public val layouts: List<LayoutContract>,
)
