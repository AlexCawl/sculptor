package org.alexcawl.sculptor.foundation.contract.modifier.size

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ModifierContract

@Serializable
@SerialName("size@fill_max_size")
data class FillMaxSize(
    @SerialName("fraction")
    val fraction: Float,
) : ModifierContract
