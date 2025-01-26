package org.alexcawl.sculptor.contract.foundation.modifier.width

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Dp

@Serializable
@SerialName("modifier@width_in")
data class WidthIn(
    @SerialName("min")
    val min: Dp,
    @SerialName("max")
    val max: Dp
) : BaseModifier
