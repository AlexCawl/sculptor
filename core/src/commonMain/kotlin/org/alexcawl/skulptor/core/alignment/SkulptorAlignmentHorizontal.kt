package org.alexcawl.skulptor.core.alignment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorProperty
import androidx.compose.ui.Alignment as ComposeAlignment
import androidx.compose.ui.Alignment.Horizontal as ComposeAlignmentHorizontal

/**
 * An interface to calculate the position of box of a certain width inside an available width.
 * [androidx.compose.ui.Alignment.Horizontal] is often used to define the horizontal alignment of a layout inside a
 * parent layout.
 */
@Serializable
sealed interface SkulptorAlignmentHorizontal : SkulptorProperty<ComposeAlignmentHorizontal> {
    @Serializable
    @SerialName("alignment@start")
    data object Start : SkulptorAlignmentHorizontal {
        override fun asCompose(): ComposeAlignmentHorizontal =
            ComposeAlignment.Start
    }

    @Serializable
    @SerialName("alignment@center_horizontally")
    data object CenterHorizontally : SkulptorAlignmentHorizontal {
        override fun asCompose(): ComposeAlignmentHorizontal =
            ComposeAlignment.CenterHorizontally
    }

    @Serializable
    @SerialName("alignment@end")
    data object End : SkulptorAlignmentHorizontal {
        override fun asCompose(): ComposeAlignmentHorizontal =
            ComposeAlignment.End
    }
}
