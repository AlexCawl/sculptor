package org.alexcawl.skulptor.core.alignment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorProperty
import androidx.compose.ui.Alignment as ComposeAlignment
import androidx.compose.ui.Alignment.Vertical as ComposeAlignmentVertical

/**
 * An interface to calculate the position of a box of a certain height inside an available
 * height. [androidx.compose.ui.Alignment.Vertical] is often used to define the vertical alignment of a
 * layout inside a parent layout.
 */
@Serializable
sealed interface SkulptorAlignmentVertical : SkulptorProperty<ComposeAlignmentVertical> {
    @Serializable
    @SerialName("alignment@top")
    data object Top : SkulptorAlignmentVertical {
        override fun asCompose(): ComposeAlignmentVertical =
            ComposeAlignment.Top
    }

    @Serializable
    @SerialName("alignment@center_vertically")
    data object CenterVertically : SkulptorAlignmentVertical {
        override fun asCompose(): ComposeAlignmentVertical =
            ComposeAlignment.CenterVertically
    }

    @Serializable
    @SerialName("alignment@bottom")
    data object Bottom : SkulptorAlignmentVertical {
        override fun asCompose(): ComposeAlignmentVertical =
            ComposeAlignment.Bottom
    }
}
