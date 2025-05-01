package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.Identifier
import org.alexcawl.sculptor.core.contract.StateContract

@Serializable
@SerialName("basic_text@state")
public data class BasicTextState(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("text")
    val text: String,
    @SerialName("soft_wrap")
    public val softWrap: Boolean,
    @SerialName("max_lines")
    public val maxLines: Int,
    @SerialName("min_lines")
    public val minLines: Int,
) : StateContract
