package org.alexcawl.skulptor.core.alignment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorProperty
import androidx.compose.ui.Alignment as ComposeAlignment

/**
 * An interface to calculate the position of a sized box inside an available space. [androidx.compose.ui.Alignment] is
 * often used to define the alignment of a layout inside a parent layout.
 */
@Serializable
sealed interface SkulptorAlignment : SkulptorProperty<ComposeAlignment> {
    @Serializable
    @SerialName("alignment@top_start")
    data object TopStart : SkulptorAlignment {
        override fun asCompose(): ComposeAlignment =
            ComposeAlignment.TopStart
    }

    @Serializable
    @SerialName("alignment@top_center")
    data object TopCenter : SkulptorAlignment {
        override fun asCompose(): ComposeAlignment =
            ComposeAlignment.TopCenter
    }

    @Serializable
    @SerialName("alignment@top_end")
    data object TopEnd : SkulptorAlignment {
        override fun asCompose(): ComposeAlignment =
            ComposeAlignment.TopEnd
    }

    @Serializable
    @SerialName("alignment@center_start")
    data object CenterStart : SkulptorAlignment {
        override fun asCompose(): ComposeAlignment =
            ComposeAlignment.CenterStart
    }

    @Serializable
    @SerialName("alignment@center")
    data object Center : SkulptorAlignment {
        override fun asCompose(): ComposeAlignment =
            ComposeAlignment.Center
    }

    @Serializable
    @SerialName("alignment@center_end")
    data object CenterEnd : SkulptorAlignment {
        override fun asCompose(): ComposeAlignment =
            ComposeAlignment.CenterEnd
    }

    @Serializable
    @SerialName("alignment@bottom_start")
    data object BottomStart : SkulptorAlignment {
        override fun asCompose(): ComposeAlignment =
            ComposeAlignment.BottomStart
    }

    @Serializable
    @SerialName("alignment@bottom_center")
    data object BottomCenter : SkulptorAlignment {
        override fun asCompose(): ComposeAlignment =
            ComposeAlignment.BottomCenter
    }

    @Serializable
    @SerialName("alignment@bottom_end")
    data object BottomEnd : SkulptorAlignment {
        override fun asCompose(): ComposeAlignment =
            ComposeAlignment.BottomEnd
    }
}
