package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TextStyle(
    @SerialName("color")
    val color: Color = Color.Unspecified,
    @SerialName("font_size")
    val fontSize: TextUnit = TextUnit.unspecified,
    @SerialName("font_style")
    val fontStyle: FontStyle = FontStyle.Normal,
    @SerialName("background")
    val background: Color = Color.Unspecified,
    @SerialName("text_decoration")
    val textDecoration: TextDecoration = TextDecoration.None,
)
