package org.alexcawl.skulptor.core.attribute

import androidx.compose.ui.Alignment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAttribute

object AlignmentWrapper {
    /**
     * An interface to calculate the position of a sized box inside an available space. [Alignment] is
     * often used to define the alignment of a layout inside a parent layout.
     */
    @Serializable
    sealed interface HorizontalAndVertical : SkulptorAttribute<Alignment> {
        @Serializable
        @SerialName("alignment@top_start")
        data object TopStart : HorizontalAndVertical {
            override fun asCompose(): Alignment =
                Alignment.TopStart
        }

        @Serializable
        @SerialName("alignment@top_center")
        data object TopCenter : HorizontalAndVertical {
            override fun asCompose(): Alignment =
                Alignment.TopCenter
        }

        @Serializable
        @SerialName("alignment@top_end")
        data object TopEnd : HorizontalAndVertical {
            override fun asCompose(): Alignment =
                Alignment.TopEnd
        }

        @Serializable
        @SerialName("alignment@center_start")
        data object CenterStart : HorizontalAndVertical {
            override fun asCompose(): Alignment =
                Alignment.CenterStart
        }

        @Serializable
        @SerialName("alignment@center")
        data object Center : HorizontalAndVertical {
            override fun asCompose(): Alignment =
                Alignment.Center
        }

        @Serializable
        @SerialName("alignment@center_end")
        data object CenterEnd : HorizontalAndVertical {
            override fun asCompose(): Alignment =
                Alignment.CenterEnd
        }

        @Serializable
        @SerialName("alignment@bottom_start")
        data object BottomStart : HorizontalAndVertical {
            override fun asCompose(): Alignment =
                Alignment.BottomStart
        }

        @Serializable
        @SerialName("alignment@bottom_center")
        data object BottomCenter : HorizontalAndVertical {
            override fun asCompose(): Alignment =
                Alignment.BottomCenter
        }

        @Serializable
        @SerialName("alignment@bottom_end")
        data object BottomEnd : HorizontalAndVertical {
            override fun asCompose(): Alignment =
                Alignment.BottomEnd
        }
    }

    /**
     * An interface to calculate the position of box of a certain width inside an available width.
     * [Alignment.Horizontal] is often used to define the horizontal alignment of a layout inside a
     * parent layout.
     */
    @Serializable
    sealed interface Horizontal : SkulptorAttribute<Alignment.Horizontal> {
        @Serializable
        @SerialName("alignment@start")
        data object Start : Horizontal {
            override fun asCompose(): Alignment.Horizontal =
                Alignment.Start
        }

        @Serializable
        @SerialName("alignment@center_horizontally")
        data object CenterHorizontally : Horizontal {
            override fun asCompose(): Alignment.Horizontal =
                Alignment.CenterHorizontally
        }

        @Serializable
        @SerialName("alignment@end")
        data object End : Horizontal {
            override fun asCompose(): Alignment.Horizontal =
                Alignment.End
        }
    }

    /**
     * An interface to calculate the position of a box of a certain height inside an available
     * height. [Alignment.Vertical] is often used to define the vertical alignment of a
     * layout inside a parent layout.
     */
    @Serializable
    sealed interface Vertical : SkulptorAttribute<Alignment.Vertical> {
        @Serializable
        @SerialName("alignment@top")
        data object Top : Vertical {
            override fun asCompose(): Alignment.Vertical =
                Alignment.Top
        }

        @Serializable
        @SerialName("alignment@center_vertically")
        data object CenterVertically : Vertical {
            override fun asCompose(): Alignment.Vertical =
                Alignment.CenterVertically
        }

        @Serializable
        @SerialName("alignment@bottom")
        data object Bottom : Vertical {
            override fun asCompose(): Alignment.Vertical =
                Alignment.Bottom
        }
    }
}
