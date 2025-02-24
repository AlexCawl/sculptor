package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.core.Logger
import org.alexcawl.sculptor.common.core.Tag

@Serializable
public data class Scaffold(
    @SerialName("sections")
    public val sections: List<Section>,

    @SerialName("forced")
    public val forcedSection: Identifier? = null,
) {
    val section: Section
        get() = sections.find { it.id == forcedSection }
            ?: sections.firstOrNull()
            ?: Logger.e(
                tag = Tag.SCAFFOLD,
                message = "No section found for $this"
            )
}
