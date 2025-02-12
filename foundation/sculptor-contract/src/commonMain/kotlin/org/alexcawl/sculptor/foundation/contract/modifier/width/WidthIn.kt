package org.alexcawl.sculptor.foundation.contract.modifier.width

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.foundation.contract.property.Dp

@Serializable
@SerialName("modifier@width_in")
data class WidthIn(
    @SerialName("min")
    val min: Dp,
    @SerialName("max")
    val max: Dp,
) : ModifierContract
