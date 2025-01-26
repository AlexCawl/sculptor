package org.alexcawl.sculptor.contract.foundation.modifier.width

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier

@Serializable
@SerialName("modifier@fill_max_width")
data class FillMaxWidth(
    @SerialName("fraction")
    val fraction: Float
) : BaseModifier