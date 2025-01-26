package org.alexcawl.sculptor.contract.foundation.modifier.height

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Dp

@Serializable
@SerialName("height@height_in")
data class HeightIn(
    @SerialName("min")
    val min: Dp,
    @SerialName("max")
    val max: Dp
) : BaseModifier
