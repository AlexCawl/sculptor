package org.alexcawl.sculptor.core.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface SculptorScreen {
    @SerialName("root")
    public val root: Identifier
}

@Serializable
@SerialName("schema")
public data class ScreenSchema(
    @SerialName("sections")
    public val sections: List<Section>,
    @SerialName("root")
    public override val root: Identifier,
    @SerialName("templates")
    public val templates: List<Section.Template> = emptyList(),
) : SculptorScreen

@Serializable
@SerialName("scaffold")
public data class ScreenScaffold(
    @SerialName("components")
    public val blocks: List<Section.Block>,
    @SerialName("root")
    public override val root: Identifier,
) : SculptorScreen
