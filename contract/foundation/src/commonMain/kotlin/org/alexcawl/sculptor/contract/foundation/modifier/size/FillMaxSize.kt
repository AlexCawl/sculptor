package org.alexcawl.sculptor.contract.foundation.modifier.size

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier

@Serializable
@SerialName("size@fill_max_size")
data class FillMaxSize(
    @SerialName("fraction")
    val fraction: Float,
) : BaseModifier
