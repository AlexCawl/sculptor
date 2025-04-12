package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Casing(
    @SerialName("components")
    public val components: List<Component>,

    @SerialName("root")
    public val root: Identifier,
)
