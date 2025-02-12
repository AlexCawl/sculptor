package org.alexcawl.sculptor.foundation.contract.modifier.height

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.foundation.contract.property.Dp

@Serializable
@SerialName("height@height")
data class Height(
    @SerialName("height")
    val height: Dp,
) : ModifierContract
