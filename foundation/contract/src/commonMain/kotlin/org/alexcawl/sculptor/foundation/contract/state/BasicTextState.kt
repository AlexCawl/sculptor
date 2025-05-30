package org.alexcawl.sculptor.foundation.contract.state

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.foundation.contract.common.TextStyle

@Serializable
@SerialName("basic_text@state")
public data class BasicTextState(
    @SerialName("text")
    val text: String,
    @SerialName("soft_wrap")
    public val softWrap: Boolean = true,
    @SerialName("max_lines")
    public val maxLines: Int = Int.MAX_VALUE,
    @SerialName("min_lines")
    public val minLines: Int = 1,
    @SerialName("text_style")
    public val textStyle: TextStyle = TextStyle(),
) : StateContract
