package org.alexcawl.sculptor.contract.foundation.modifier.size

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Dp
import org.alexcawl.sculptor.contract.common.DpSize

@Serializable
@SerialName("size@size")
data class Size(
    @SerialName("width")
    val width: Dp,
    @SerialName("height")
    val height: Dp,
) : BaseModifier {
    constructor(all: Dp) : this(
        width = all,
        height = all
    )

    constructor(size: DpSize) : this(
        width = size.width,
        height = size.height,
    )
}
