package org.alexcawl.sculptor.contract.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Alignment {
    @Serializable
    sealed interface Horizontal : Alignment {
        @SerialName("horizontal@start")
        data object Start : Horizontal

        @SerialName("horizontal@center")
        data object Center : Horizontal

        @SerialName("horizontal@end")
        data object End : Horizontal
    }

    @Serializable
    sealed interface Vertical : Alignment {
        @SerialName("vertical@top")
        data object Top : Vertical

        @SerialName("vertical@center")
        data object Center : Vertical

        @SerialName("vertical@bottom")
        data object Bottom : Vertical
    }

    @Serializable
    @SerialName("both")
    sealed interface Both : Alignment {
        @SerialName("both@top_start")
        data object TopStart : Both

        @SerialName("both@top_center")
        data object TopCenter : Both

        @SerialName("both@top_end")
        data object TopEnd : Both

        @SerialName("both@center_start")
        data object CenterStart : Both

        @SerialName("both@center")
        data object Center : Both

        @SerialName("both@center_end")
        data object CenterEnd : Both

        @SerialName("both@bottom_start")
        data object BottomStart : Both

        @SerialName("both@bottom_center")
        data object BottomCenter : Both

        @SerialName("both@bottom_end")
        data object BottomEnd : Both
    }
}
