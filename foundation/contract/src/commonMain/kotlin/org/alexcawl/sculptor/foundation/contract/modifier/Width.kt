package org.alexcawl.sculptor.foundation.contract.modifier

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.ModifierContract
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Dp

@Serializable
@SerialName("modifier@width")
public data class Width(
    @SerialName("width")
    val width: Dp,
) : ModifierContract

@Serializable
@SerialName("modifier@required_width_in")
public data class RequiredWidthIn(
    @SerialName("min")
    val min: Dp,
    @SerialName("max")
    val max: Dp,
) : ModifierContract

@Serializable
@SerialName("modifier@fill_max_width")
public data class FillMaxWidth(
    @SerialName("fraction")
    val fraction: Float,
) : ModifierContract

@Serializable
@SerialName("modifier@required_width")
public data class RequiredWidth(
    @SerialName("width")
    val width: Dp,
) : ModifierContract

@Serializable
@SerialName("modifier@width_in")
public data class WidthIn(
    @SerialName("min")
    val min: Dp,
    @SerialName("max")
    val max: Dp,
) : ModifierContract

@Serializable
@SerialName("modifier@wrap_content_width")
public data class WrapContentWidth(
    @SerialName("align")
    val align: Alignment.Horizontal,
    @SerialName("unbounded")
    val unbounded: Boolean,
) : ModifierContract
