package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface Arrangement {
    @Serializable
    @SerialName("center")
    public data object Center : Arrangement

    @Serializable
    @SerialName("space_evenly")
    public data object SpaceEvenly : Arrangement

    @Serializable
    @SerialName("space_between")
    public data object SpaceBetween : Arrangement

    @Serializable
    @SerialName("space_around")
    public data object SpaceAround : Arrangement

    @Serializable
    @SerialName("spaced_by")
    public data class SpacedBy(val space: Dp) : Arrangement

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

        @Serializable
        @SerialName("horizontal@spaced_by")
        public data class SpacedBy(val space: Dp, val alignment: Alignment.Horizontal) : Horizontal

        @Serializable
        @SerialName("horizontal@aligned")
        public data class Aligned(val alignment: Alignment.Horizontal) : Horizontal
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

        @Serializable
        @SerialName("vertical@spaced_by")
        public data class SpacedBy(val space: Dp, val alignment: Alignment.Vertical) : Vertical

        @Serializable
        @SerialName("vertical@aligned")
        public data class Aligned(val alignment: Alignment.Vertical) : Vertical
    }
}
