package org.alexcawl.skulptor.core.alignment

import androidx.compose.ui.Alignment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAttribute

/**
 * An interface to calculate the position of a box of a certain height inside an available
 * height. [Alignment.Vertical] is often used to define the vertical alignment of a
 * layout inside a parent layout.
 */
@Serializable
sealed interface SAlignmentVertical : SkulptorAttribute<Alignment.Vertical> {
    @Serializable
    @SerialName("alignment@top")
    data object Top : SAlignmentVertical {
        override fun asCompose(): Alignment.Vertical =
            Alignment.Top
    }

    @Serializable
    @SerialName("alignment@center_vertically")
    data object CenterVertically : SAlignmentVertical {
        override fun asCompose(): Alignment.Vertical =
            Alignment.CenterVertically
    }

    @Serializable
    @SerialName("alignment@bottom")
    data object Bottom : SAlignmentVertical {
        override fun asCompose(): Alignment.Vertical =
            Alignment.Bottom
    }
}
