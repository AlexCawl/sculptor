package org.alexcawl.skulptor.core.alignment

import androidx.compose.ui.Alignment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAttribute

/**
 * An interface to calculate the position of a sized box inside an available space. [Alignment] is
 * often used to define the alignment of a layout inside a parent layout.
 */
@Serializable
sealed interface SAlignmentHorizontalAndVertical : SkulptorAttribute<Alignment> {
    @Serializable
    @SerialName("alignment@top_start")
    data object TopStart : SAlignmentHorizontalAndVertical {
        override fun asCompose(): Alignment =
            Alignment.TopStart
    }

    @Serializable
    @SerialName("alignment@top_center")
    data object TopCenter : SAlignmentHorizontalAndVertical {
        override fun asCompose(): Alignment =
            Alignment.TopCenter
    }

    @Serializable
    @SerialName("alignment@top_end")
    data object TopEnd : SAlignmentHorizontalAndVertical {
        override fun asCompose(): Alignment =
            Alignment.TopEnd
    }

    @Serializable
    @SerialName("alignment@center_start")
    data object CenterStart : SAlignmentHorizontalAndVertical {
        override fun asCompose(): Alignment =
            Alignment.CenterStart
    }

    @Serializable
    @SerialName("alignment@center")
    data object Center : SAlignmentHorizontalAndVertical {
        override fun asCompose(): Alignment =
            Alignment.Center
    }

    @Serializable
    @SerialName("alignment@center_end")
    data object CenterEnd : SAlignmentHorizontalAndVertical {
        override fun asCompose(): Alignment =
            Alignment.CenterEnd
    }

    @Serializable
    @SerialName("alignment@bottom_start")
    data object BottomStart : SAlignmentHorizontalAndVertical {
        override fun asCompose(): Alignment =
            Alignment.BottomStart
    }

    @Serializable
    @SerialName("alignment@bottom_center")
    data object BottomCenter : SAlignmentHorizontalAndVertical {
        override fun asCompose(): Alignment =
            Alignment.BottomCenter
    }

    @Serializable
    @SerialName("alignment@bottom_end")
    data object BottomEnd : SAlignmentHorizontalAndVertical {
        override fun asCompose(): Alignment =
            Alignment.BottomEnd
    }
}
