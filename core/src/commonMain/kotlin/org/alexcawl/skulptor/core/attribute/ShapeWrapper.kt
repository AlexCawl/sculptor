package org.alexcawl.skulptor.core.attribute

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAttribute

@Serializable
sealed interface ShapeWrapper : SkulptorAttribute<Shape> {
    @Serializable
    @SerialName("shape@rectangle")
    data object Rectangle : ShapeWrapper {
        override fun asCompose(): Shape =
            RectangleShape
    }

    @Serializable
    @SerialName("shape@circle")
    data object Circle : ShapeWrapper {
        override fun asCompose(): Shape =
            CircleShape
    }

    @Serializable
    @SerialName("shape@rounded_corner")
    data class RoundedCorner(
        val dp: DpWrapper
    ) : ShapeWrapper {
        override fun asCompose(): Shape =
            RoundedCornerShape(dp.asCompose())
    }
}
