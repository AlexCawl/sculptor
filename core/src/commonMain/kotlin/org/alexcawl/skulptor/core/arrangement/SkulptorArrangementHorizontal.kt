package org.alexcawl.skulptor.core.arrangement

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorProperty
import org.alexcawl.skulptor.core.alignment.SkulptorAlignmentHorizontal
import org.alexcawl.skulptor.core.dimension.DimensionDp
import androidx.compose.foundation.layout.Arrangement as ComposeArrangement
import androidx.compose.foundation.layout.Arrangement.Horizontal as ComposeHorizontalArrangement

/**
 * Used to specify the horizontal arrangement of the layout's children in layouts like [androidx.compose.foundation.layout.Row].
 */
@Serializable
sealed interface SkulptorArrangementHorizontal : SkulptorProperty<ComposeHorizontalArrangement> {
    /**
     * Place children horizontally such that they are as close as possible to the beginning of the
     * horizontal axis (left if the layout direction is LTR, right otherwise).
     * Visually: 123#### for LTR and ####321.
     */
    @Serializable
    @SerialName("arrangement@start")
    data object Start : SkulptorArrangementHorizontal {
        override fun asCompose(): ComposeHorizontalArrangement =
            ComposeArrangement.Start
    }

    /**
     * Place children horizontally such that they are as close as possible to the end of the main
     * axis.
     * Visually: ####123 for LTR and 321#### for RTL.
     */
    @Serializable
    @SerialName("arrangement@end")
    data object End : SkulptorArrangementHorizontal {
        override fun asCompose(): ComposeHorizontalArrangement =
            ComposeArrangement.End
    }

    /**
     * Place children horizontally such that each two adjacent ones are spaced by a fixed [space]
     * distance. The spacing will be subtracted from the available width that the children
     * can occupy. An [alignment] can be specified to align the spaced children horizontally
     * inside the parent, in case there is empty width remaining. The [space] can be negative,
     * in which case children will overlap.
     *
     * @param space The space between adjacent children.
     * @param alignment The alignment of the spaced children inside the parent.
     */
    @Serializable
    @SerialName("arrangement@spaced_by_alignment_horizontal")
    data class SpacedByAlignmentHorizontal(
        val space: DimensionDp,
        val alignment: SkulptorAlignmentHorizontal
    ) : SkulptorArrangementHorizontal {
        override fun asCompose(): ComposeHorizontalArrangement =
            ComposeArrangement.spacedBy(
                space = space.asCompose(),
                alignment = alignment.asCompose()
            )
    }
}
