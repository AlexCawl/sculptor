package org.alexcawl.skulptor.contract.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Arrangement {
    @Serializable
    sealed interface Either : Arrangement {
        @Serializable
        @SerialName("either@center")
        data object Center : Either

        @Serializable
        @SerialName("either@space_evenly")
        data object SpaceEvenly : Either

        @Serializable
        @SerialName("either@space_between")
        data object SpaceBetween : Either

        @Serializable
        @SerialName("either@space_around")
        data object SpaceAround : Either

        @Serializable
        @SerialName("either@spaced_by")
        data class SpacedBy(val space: Dp) : Either
    }

    @Serializable
    sealed interface Horizontal : Arrangement {
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
    sealed interface Vertical : Arrangement {
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
