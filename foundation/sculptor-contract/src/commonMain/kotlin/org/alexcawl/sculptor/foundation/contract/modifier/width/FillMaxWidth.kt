package org.alexcawl.sculptor.foundation.contract.modifier.width

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.layout.ModifierContract

@Serializable
@SerialName("modifier@fill_max_width")
data class FillMaxWidth(
    @SerialName("fraction")
    val fraction: Float,
) : ModifierContract
