package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("text_decoration")
public sealed interface TextDecoration {
    @Serializable
    @SerialName("none")
    public data object None : TextDecoration

    @Serializable
    @SerialName("underline")
    public data object Underline : TextDecoration

    @Serializable
    @SerialName("line_through")
    public data object LineThrough : TextDecoration

    @Serializable
    @SerialName("combined")
    public data class Combined(
        @SerialName("decorations")
        val decorations: List<TextDecoration>,
    ) : TextDecoration
}
