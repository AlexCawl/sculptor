package org.alexcawl.sculptor.foundation.contract.modifier.background

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ContractModifier
import org.alexcawl.sculptor.foundation.contract.property.Color
import org.alexcawl.sculptor.foundation.contract.property.Shape

@Serializable
@SerialName("background@color")
data class Background(
    @SerialName("color")
    val color: Color,
    @SerialName("shape")
    val shape: Shape,
) : ContractModifier
