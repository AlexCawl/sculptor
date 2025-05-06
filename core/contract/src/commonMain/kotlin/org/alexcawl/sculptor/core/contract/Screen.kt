package org.alexcawl.sculptor.core.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.Section.Block
import org.alexcawl.sculptor.core.contract.Section.Template

/**
 * Represents the base interface for a screen in the Sculptor framework.
 * All screen types must implement this interface.
 *
 * @property root The unique identifier for the root element of the screen.
 */
@Serializable
public sealed interface Screen {
    @SerialName("root")
    public val root: Identifier
}

/**
 * Represents a screen schema in the Sculptor framework.
 * This class is used to define the structure of a screen using templates and sections.
 *
 * @property templates A list of [Template] objects that define reusable components.
 * @property sections A list of [Section] objects that define the layout and structure of the screen.
 * @property root The unique identifier for the root element of the screen.
 */
@Serializable
@SerialName("schema")
public data class ScreenSchema(
    @SerialName("templates")
    public val templates: List<Template>,
    @SerialName("sections")
    public val sections: List<Section>,
    @SerialName("root")
    public override val root: Identifier,
) : Screen

/**
 * Represents a scaffold screen in the Sculptor framework.
 * This class is used to define a screen using a list of blocks.
 *
 * @property blocks A list of [Block] objects that define the components of the screen.
 * @property root The unique identifier for the root element of the screen.
 */
@Serializable
@SerialName("scaffold")
public data class ScreenScaffold(
    @SerialName("blocks")
    public val blocks: List<Block>,
    @SerialName("root")
    public override val root: Identifier,
) : Screen
