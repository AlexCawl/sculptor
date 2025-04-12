package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Schema(
    @SerialName("templates")
    public val templates: List<Template>,

    @SerialName("sections")
    public val sections: List<Section>,

    @SerialName("root")
    public val root: Identifier,
)
