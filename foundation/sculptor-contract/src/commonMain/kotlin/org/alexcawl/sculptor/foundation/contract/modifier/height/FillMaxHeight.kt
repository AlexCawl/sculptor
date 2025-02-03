package org.alexcawl.sculptor.foundation.contract.modifier.height

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ContractModifier

@Serializable
@SerialName("height@fill_max_height")
data class FillMaxHeight(
    @SerialName("fraction")
    val fraction: Float,
) : ContractModifier
