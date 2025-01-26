package org.alexcawl.sculptor.contract.foundation.modifier.height

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier

@Serializable
@SerialName("height@fill_max_height")
data class FillMaxHeight(
    @SerialName("fraction")
    val fraction: Float,
) : BaseModifier
