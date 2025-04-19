package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface SculptorScreen {
    @SerialName("root")
    public val root: Identifier

    @Serializable
    public data class Schema(
        @SerialName("sections")
        public val sections: List<Section>,
        @SerialName("root")
        public override val root: Identifier,
        @SerialName("templates")
        public val templates: List<Template> = emptyList(),
    ) : SculptorScreen

    @Serializable
    public data class Scaffold(
        @SerialName("components")
        public val blocks: List<Block>,
        @SerialName("root")
        public override val root: Identifier,
    ) : SculptorScreen
}
