package org.alexcawl.sculptor.foundation.contract.modifier

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.ModifierContract
import org.alexcawl.sculptor.foundation.contract.common.Dp

@Serializable
@SerialName("modifier@padding")
public data class Padding(
    @SerialName("start")
    val start: Dp,
    @SerialName("top")
    val top: Dp,
    @SerialName("end")
    val end: Dp,
    @SerialName("bottom")
    val bottom: Dp,
) : ModifierContract {
    public constructor(all: Dp) : this(all, all, all, all)

    public constructor(horizontal: Dp, vertical: Dp) : this(horizontal, vertical, horizontal, vertical)
}
