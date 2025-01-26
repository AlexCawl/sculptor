package org.alexcawl.sculptor.contract.foundation.modifier.background

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Color
import org.alexcawl.sculptor.contract.common.Shape

@Serializable
@SerialName("background@color")
data class Background(
    @SerialName("color")
    val color: Color,
    @SerialName("shape")
    val shape: Shape
) : BaseModifier
