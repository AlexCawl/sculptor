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
sealed interface SAlignment : SkulptorAttribute<Alignment> {
    @Serializable
    @SerialName("alignment@top_start")
    data object TopStart : SAlignment {
        override fun asCompose(): Alignment =
            Alignment.TopStart
    }

    @Serializable
    @SerialName("alignment@top_center")
    data object TopCenter : SAlignment {
        override fun asCompose(): Alignment =
            Alignment.TopCenter
    }

    @Serializable
    @SerialName("alignment@top_end")
    data object TopEnd : SAlignment {
        override fun asCompose(): Alignment =
            Alignment.TopEnd
    }

    @Serializable
    @SerialName("alignment@center_start")
    data object CenterStart : SAlignment {
        override fun asCompose(): Alignment =
            Alignment.CenterStart
    }

    @Serializable
    @SerialName("alignment@center")
    data object Center : SAlignment {
        override fun asCompose(): Alignment =
            Alignment.Center
    }

    @Serializable
    @SerialName("alignment@center_end")
    data object CenterEnd : SAlignment {
        override fun asCompose(): Alignment =
            Alignment.CenterEnd
    }

    @Serializable
    @SerialName("alignment@bottom_start")
    data object BottomStart : SAlignment {
        override fun asCompose(): Alignment =
            Alignment.BottomStart
    }

    @Serializable
    @SerialName("alignment@bottom_center")
    data object BottomCenter : SAlignment {
        override fun asCompose(): Alignment =
            Alignment.BottomCenter
    }

    @Serializable
    @SerialName("alignment@bottom_end")
    data object BottomEnd : SAlignment {
        override fun asCompose(): Alignment =
            Alignment.BottomEnd
    }
}
