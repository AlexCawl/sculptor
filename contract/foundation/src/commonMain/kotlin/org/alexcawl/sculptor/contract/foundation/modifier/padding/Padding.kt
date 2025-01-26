package org.alexcawl.sculptor.contract.foundation.modifier.padding

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Dp

@Serializable
@SerialName("modifier@padding")
data class Padding(
    @SerialName("start")
    val start: Dp,
    @SerialName("top")
    val top: Dp,
    @SerialName("end")
    val end: Dp,
    @SerialName("bottom")
    val bottom: Dp,
) : BaseModifier {
    constructor(all: Dp) : this(all, all, all, all)

    constructor(horizontal: Dp, vertical: Dp) : this(horizontal, vertical, horizontal, vertical)
}
