package org.alexcawl.sculptor.foundation.contract.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Shape {
    @Serializable
    @SerialName("rectangle")
    data object Rectangle : Shape

    @Serializable
    @SerialName("circle")
    data object Circle : Shape

    @Serializable
    sealed interface RoundedCorner : Shape {
        @Serializable
        @SerialName("rounded_corner@dp")
        data class DPixel(
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
        data class Percent(
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
    sealed interface CutCorner : Shape {
        @Serializable
        @SerialName("cut_corner@dp")
        data class DPixel(
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
        data class Percent(
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
