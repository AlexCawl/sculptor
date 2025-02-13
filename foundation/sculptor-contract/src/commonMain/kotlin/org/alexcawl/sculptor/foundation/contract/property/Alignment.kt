package org.alexcawl.sculptor.foundation.contract.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Alignment {
    @Serializable
    sealed interface Horizontal {
        @SerialName("horizontal@start")
        data object Start : Horizontal

        @SerialName("horizontal@center")
        data object Center : Horizontal

        @SerialName("horizontal@end")
        data object End : Horizontal
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
    }

    @Serializable
    @SerialName("top_start")
    data object TopStart : Alignment

    @Serializable
    @SerialName("top_center")
    data object TopCenter : Alignment

    @Serializable
    @SerialName("top_end")
    data object TopEnd : Alignment

    @Serializable
    @SerialName("center_start")
    data object CenterStart : Alignment

    @Serializable
    @SerialName("center")
    data object Center : Alignment

    @Serializable
    @SerialName("center_end")
    data object CenterEnd : Alignment

    @Serializable
    @SerialName("bottom_start")
    data object BottomStart : Alignment

    @Serializable
    @SerialName("bottom_center")
    data object BottomCenter : Alignment

    @Serializable
    @SerialName("bottom_end")
    data object BottomEnd : Alignment
}
