package org.alexcawl.skulptor.core.provider

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.Provider

@Serializable
sealed interface ShapeProvider : Provider<Shape> {
    @Serializable
    @SerialName("rectangle")
    data object Rectangle : ShapeProvider {
        override fun invoke(): Shape = RectangleShape
    }

    @Serializable
    @SerialName("circle")
    data object Circle : ShapeProvider {
        override fun invoke(): Shape = CircleShape
    }

    @Serializable
    sealed interface RoundedCorner : ShapeProvider {
        @Serializable
        @SerialName("rounded_corner@dp")
        data class Dp(
            val topStart: DpProvider,
            val topEnd: DpProvider,
            val bottomEnd: DpProvider,
            val bottomStart: DpProvider
        ) : RoundedCorner {
            override fun invoke(): Shape = RoundedCornerShape(
                topStart = topStart(),
                topEnd = topEnd(),
                bottomEnd = bottomEnd(),
                bottomStart = bottomStart()
            )
        }

        @Serializable
        @SerialName("rounded_corner@percent")
        data class Percent(
            val topStart: Int,
            val topEnd: Int,
            val bottomEnd: Int,
            val bottomStart: Int
        ) : RoundedCorner {
            override fun invoke(): Shape = RoundedCornerShape(
                topStartPercent = topStart,
                topEndPercent = topEnd,
                bottomEndPercent = bottomEnd,
                bottomStartPercent = bottomStart
            )
        }
    }

    @Serializable
    sealed interface CutCorner : ShapeProvider {
        @Serializable
        @SerialName("cut_corner@dp")
        data class Dp(
            val topStart: DpProvider,
            val topEnd: DpProvider,
            val bottomEnd: DpProvider,
            val bottomStart: DpProvider
        ) : RoundedCorner {
            override fun invoke(): Shape = CutCornerShape(
                topStart = topStart(),
                topEnd = topEnd(),
                bottomEnd = bottomEnd(),
                bottomStart = bottomStart()
            )
        }

        @Serializable
        @SerialName("cut_corner@percent")
        data class Percent(
            val topStart: Int,
            val topEnd: Int,
            val bottomEnd: Int,
            val bottomStart: Int
        ) : RoundedCorner {
            override fun invoke(): Shape = CutCornerShape(
                topStartPercent = topStart,
                topEndPercent = topEnd,
                bottomEndPercent = bottomEnd,
                bottomStartPercent = bottomStart
            )
        }
    }
}
