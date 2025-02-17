package org.alexcawl.sculptor.foundation.contract.modifier

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Dp
import org.alexcawl.sculptor.foundation.contract.common.DpSize

@Serializable
@SerialName("size@size")
public data class Size(
    @SerialName("width")
    val width: Dp,
    @SerialName("height")
    val height: Dp,
) : ModifierContract {
    public constructor(all: Dp) : this(
        width = all,
        height = all
    )

    public constructor(size: DpSize) : this(
        width = size.width,
        height = size.height,
    )
}

@Serializable
@SerialName("size@fill_max_size")
public data class FillMaxSize(
    @SerialName("fraction")
    val fraction: Float,
) : ModifierContract

@Serializable
@SerialName("size@required_size")
public data class RequiredSize(
    @SerialName("size")
    val size: Dp,
) : ModifierContract

@Serializable
@SerialName("size@required_size_in")
public data class RequiredSizeIn(
    @SerialName("min")
    val min: Dp,
    @SerialName("max")
    val max: Dp,
) : ModifierContract

@Serializable
@SerialName("size@size_in")
public data class SizeIn(
    @SerialName("min")
    val min: Dp,
    @SerialName("max")
    val max: Dp,
) : ModifierContract

@Serializable
@SerialName("size@wrap_content_size")
public data class WrapContentSize(
    @SerialName("align")
    val align: Alignment,
    @SerialName("unbounded")
    val unbounded: Boolean,
) : ModifierContract