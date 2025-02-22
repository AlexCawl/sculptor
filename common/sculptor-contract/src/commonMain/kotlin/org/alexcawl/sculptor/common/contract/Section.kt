package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.core.Logger
import org.alexcawl.sculptor.common.core.Tag

@Serializable
public data class Section(
    @SerialName("id")
    public val id: Identifier,

    @SerialName("modifiers")
    public val modifiers: List<ModifierContract>,

    @SerialName("states")
    public val states: List<StateContract>,

    @SerialName("forced")
    public val forcedState: Identifier? = null,
) {
    val state: StateContract
        get() = states.find { it.id == forcedState }
            ?: states.firstOrNull()
            ?: Logger.e(
                tag = Tag.SECTION,
                message = "No state found for $this"
            )
}
