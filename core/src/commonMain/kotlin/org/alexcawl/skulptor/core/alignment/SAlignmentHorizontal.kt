package org.alexcawl.skulptor.core.alignment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAttribute
import androidx.compose.ui.Alignment

/**
 * An interface to calculate the position of box of a certain width inside an available width.
 * [Alignment.Horizontal] is often used to define the horizontal alignment of a layout inside a
 * parent layout.
 */
@Serializable
sealed interface SAlignmentHorizontal : SkulptorAttribute<Alignment.Horizontal> {
    @Serializable
    @SerialName("alignment@start")
    data object Start : SAlignmentHorizontal {
        override fun asCompose(): Alignment.Horizontal =
            Alignment.Start
    }

    @Serializable
    @SerialName("alignment@center_horizontally")
    data object CenterHorizontally : SAlignmentHorizontal {
        override fun asCompose(): Alignment.Horizontal =
            Alignment.CenterHorizontally
    }

    @Serializable
    @SerialName("alignment@end")
    data object End : SAlignmentHorizontal {
        override fun asCompose(): Alignment.Horizontal =
            Alignment.End
    }
}
