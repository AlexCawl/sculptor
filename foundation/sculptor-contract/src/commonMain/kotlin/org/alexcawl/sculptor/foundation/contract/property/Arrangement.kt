package org.alexcawl.sculptor.foundation.contract.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Arrangement {
    @Serializable
    @SerialName("center")
    data object Center : Arrangement

    @Serializable
    @SerialName("space_evenly")
    data object SpaceEvenly : Arrangement

    @Serializable
    @SerialName("space_between")
    data object SpaceBetween : Arrangement

    @Serializable
    @SerialName("space_around")
    data object SpaceAround : Arrangement

    @Serializable
    @SerialName("spaced_by")
    data class SpacedBy(val space: Dp) : Arrangement

    @Serializable
    sealed interface Horizontal {
        @Serializable
        @SerialName("horizontal@start")
        data object Start : Horizontal

        @Serializable
        @SerialName("horizontal@center")
        data object Center : Horizontal

        @Serializable
        @SerialName("horizontal@end")
        data object End : Horizontal

        @Serializable
        @SerialName("horizontal@spaced_by")
        data class SpacedBy(val space: Dp, val alignment: Alignment.Horizontal) : Horizontal

        @Serializable
        @SerialName("horizontal@aligned")
        data class Aligned(val alignment: Alignment.Horizontal) : Horizontal
    }

    @Serializable
    sealed interface Vertical {
        @Serializable
        @SerialName("vertical@top")
        data object Top : Vertical

        @Serializable
        @SerialName("vertical@center")
        data object Center : Vertical

        @Serializable
        @SerialName("vertical@bottom")
        data object Bottom : Vertical

        @Serializable
        @SerialName("vertical@spaced_by")
        data class SpacedBy(val space: Dp, val alignment: Alignment.Vertical) : Vertical

        @Serializable
        @SerialName("vertical@aligned")
        data class Aligned(val alignment: Alignment.Vertical) : Vertical
    }
}
