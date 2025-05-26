package org.alexcawl.sculptor.foundation.contract.modifier

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.ModifierContract
import org.alexcawl.sculptor.foundation.contract.common.Color
import org.alexcawl.sculptor.foundation.contract.common.Shape

@Serializable
@SerialName("background@color")
public data class Background(
    @SerialName("color")
    val color: Color,
    @SerialName("shape")
    val shape: Shape = Shape.Rectangle,
) : ModifierContract
