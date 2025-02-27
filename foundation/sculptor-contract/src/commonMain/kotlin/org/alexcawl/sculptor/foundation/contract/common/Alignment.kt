package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface Alignment {
    @Serializable
    public sealed interface Horizontal {
        @Serializable
        @SerialName("horizontal@start")
        public data object Start : Horizontal

        @Serializable
        @SerialName("horizontal@center")
        public data object Center : Horizontal

        @Serializable
        @SerialName("horizontal@end")
        public data object End : Horizontal
    }

    @Serializable
    public sealed interface Vertical {
        @Serializable
        @SerialName("vertical@top")
        public data object Top : Vertical

        @Serializable
        @SerialName("vertical@center")
        public data object Center : Vertical

        @Serializable
        @SerialName("vertical@bottom")
        public data object Bottom : Vertical
    }

    @Serializable
    @SerialName("top_start")
    public data object TopStart : Alignment

    @Serializable
    @SerialName("top_center")
    public data object TopCenter : Alignment

    @Serializable
    @SerialName("top_end")
    public data object TopEnd : Alignment

    @Serializable
    @SerialName("center_start")
    public data object CenterStart : Alignment

    @Serializable
    @SerialName("center")
    public data object Center : Alignment

    @Serializable
    @SerialName("center_end")
    public data object CenterEnd : Alignment

    @Serializable
    @SerialName("bottom_start")
    public data object BottomStart : Alignment

    @Serializable
    @SerialName("bottom_center")
    public data object BottomCenter : Alignment

    @Serializable
    @SerialName("bottom_end")
    public data object BottomEnd : Alignment
}
