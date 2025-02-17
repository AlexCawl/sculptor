package org.alexcawl.sculptor.foundation.contract.modifier

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Dp

@Serializable
@SerialName("height@height")
public data class Height(
    @SerialName("height")
    val height: Dp,
) : ModifierContract

@Serializable
@SerialName("height@fill_max_height")
public data class FillMaxHeight(
    @SerialName("fraction")
    val fraction: Float,
) : ModifierContract

@Serializable
@SerialName("height@height_in")
public data class HeightIn(
    @SerialName("min")
    val min: Dp,
    @SerialName("max")
    val max: Dp,
) : ModifierContract

@Serializable
@SerialName("height@required_height")
public data class RequiredHeight(
    @SerialName("height")
    val height: Dp,
) : ModifierContract

@Serializable
@SerialName("height@required_height_in")
public data class RequiredHeightIn(
    @SerialName("min")
    val min: Dp,
    @SerialName("max")
    val max: Dp,
) : ModifierContract

@Serializable
@SerialName("height@wrap_content_height")
public data class WrapContentHeight(
    @SerialName("align")
    val align: Alignment.Vertical,
    @SerialName("unbounded")
    val unbounded: Boolean,
) : ModifierContract
