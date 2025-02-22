package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.StateContract

@Serializable
@SerialName("basic_text@state")
public data class BasicTextState(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("soft_wrap")
    public val softWrap: Boolean,
    @SerialName("max_lines")
    public val maxLines: Int,
    @SerialName("min_lines")
    public val minLines: Int,
    @SerialName("text")
    public val textType: TextType,
) : StateContract {
    @Serializable
    public sealed interface TextType {
        @Serializable
        @SerialName("static")
        public data class Static(
            @SerialName("text")
            val text: String,
        ) : TextType

        @Serializable
        @SerialName("dynamic")
        public data class Dynamic(
            @SerialName("text_identifier")
            val textIdentifier: Identifier,
        ) : TextType
    }
}
