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
        @SerialName("vertical@top")
        data object Top : Vertical

        @SerialName("vertical@center")
        data object Center : Vertical

        @SerialName("vertical@bottom")
        data object Bottom : Vertical
    }

    @SerialName("top_start")
    data object TopStart : Alignment

    @SerialName("top_center")
    data object TopCenter : Alignment

    @SerialName("top_end")
    data object TopEnd : Alignment

    @SerialName("center_start")
    data object CenterStart : Alignment

    @SerialName("center")
    data object Center : Alignment

    @SerialName("center_end")
    data object CenterEnd : Alignment

    @SerialName("bottom_start")
    data object BottomStart : Alignment

    @SerialName("bottom_center")
    data object BottomCenter : Alignment

    @SerialName("bottom_end")
    data object BottomEnd : Alignment
}
