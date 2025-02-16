package org.alexcawl.sculptor.foundation.contract.modifier.size

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.foundation.contract.property.Dp
import org.alexcawl.sculptor.foundation.contract.property.DpSize

@Serializable
@SerialName("size@size")
data class Size(
    @SerialName("width")
    val width: Dp,
    @SerialName("height")
    val height: Dp,
) : ModifierContract {
    constructor(all: Dp) : this(
        width = all,
        height = all
    )

    constructor(size: DpSize) : this(
        width = size.width,
        height = size.height,
    )
}
