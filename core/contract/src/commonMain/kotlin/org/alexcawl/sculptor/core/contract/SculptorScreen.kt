package org.alexcawl.sculptor.core.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.Section.Block
import org.alexcawl.sculptor.core.contract.Section.Template

@Serializable
public sealed interface SculptorScreen {
    @SerialName("root")
    public val root: Identifier
}

@Serializable
@SerialName("schema")
public data class SculptorScreenSchema(
    @SerialName("templates")
    public val templates: List<Template>,
    @SerialName("sections")
    public val sections: List<Section>,
    @SerialName("root")
    public override val root: Identifier,
) : SculptorScreen

@Serializable
@SerialName("scaffold")
public data class SculptorScreenScaffold(
    @SerialName("components")
    public val blocks: List<Block>,
    @SerialName("root")
    public override val root: Identifier,
) : SculptorScreen
