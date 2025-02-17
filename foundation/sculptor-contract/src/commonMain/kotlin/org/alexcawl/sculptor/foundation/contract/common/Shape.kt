package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface Shape {
    @Serializable
    @SerialName("rectangle")
    public data object Rectangle : Shape

    @Serializable
    @SerialName("circle")
    public data object Circle : Shape

    @Serializable
    public sealed interface RoundedCorner : Shape {
        @Serializable
        @SerialName("rounded_corner@dp")
        public data class DPixel(
            @SerialName("top_start")
            val topStart: Dp,
            @SerialName("top_end")
            val topEnd: Dp,
            @SerialName("bottom_end")
            val bottomEnd: Dp,
            @SerialName("bottom_start")
            val bottomStart: Dp
        ) : RoundedCorner

        @Serializable
        @SerialName("rounded_corner@percent")
        public data class Percent(
            @SerialName("top_start")
            val topStart: Int,
            @SerialName("top_end")
            val topEnd: Int,
            @SerialName("bottom_end")
            val bottomEnd: Int,
            @SerialName("bottom_start")
            val bottomStart: Int
        ) : RoundedCorner
    }

    @Serializable
    public sealed interface CutCorner : Shape {
        @Serializable
        @SerialName("cut_corner@dp")
        public data class DPixel(
            @SerialName("top_start")
            val topStart: Dp,
            @SerialName("top_end")
            val topEnd: Dp,
            @SerialName("bottom_end")
            val bottomEnd: Dp,
            @SerialName("bottom_start")
            val bottomStart: Dp
        ) : CutCorner

        @Serializable
        @SerialName("cut_corner@percent")
        public data class Percent(
            @SerialName("top_start")
            val topStart: Int,
            @SerialName("top_end")
            val topEnd: Int,
            @SerialName("bottom_end")
            val bottomEnd: Int,
            @SerialName("bottom_start")
            val bottomStart: Int
        ) : CutCorner
    }
}

public fun RoundedCorner(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp
): Shape = Shape.RoundedCorner.DPixel(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart
)

public fun RoundedCorner(
    topStart: Int,
    topEnd: Int,
    bottomEnd: Int,
    bottomStart: Int
): Shape = Shape.RoundedCorner.Percent(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart
)

public fun CutCorner(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp
): Shape = Shape.CutCorner.DPixel(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart
)

public fun CutCorner(
    topStart: Int,
    topEnd: Int,
    bottomEnd: Int,
    bottomStart: Int
): Shape = Shape.CutCorner.Percent(
    topStart = topStart,
    topEnd = topEnd,
    bottomEnd = bottomEnd,
    bottomStart = bottomStart
)
