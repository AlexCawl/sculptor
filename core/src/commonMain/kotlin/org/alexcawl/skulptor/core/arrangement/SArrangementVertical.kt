package org.alexcawl.skulptor.core.arrangement

import androidx.compose.foundation.layout.Arrangement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SAttribute
import org.alexcawl.skulptor.core.alignment.SAlignmentVertical
import org.alexcawl.skulptor.core.dimension.DimensionDp

/**
 * Used to specify the vertical arrangement of the layout's children in layouts like [androidx.compose.foundation.layout.Column].
 */
@Serializable
sealed interface SArrangementVertical : SAttribute<Arrangement.Vertical> {
    /**
     * Place children vertically such that they are as close as possible to the top of the main
     * axis.
     * Visually: (top) 123#### (bottom)
     */
    @Serializable
    @SerialName("arrangement@top")
    data object Top : SArrangementVertical {
        override fun asCompose(): Arrangement.Vertical =
            Arrangement.Top
    }

    /**
     * Place children vertically such that they are as close as possible to the bottom of the main
     * axis.
     * Visually: (top) ####123 (bottom)
     */
    @Serializable
    @SerialName("arrangement@bottom")
    data object Bottom : SArrangementVertical {
        override fun asCompose(): Arrangement.Vertical =
            Arrangement.Bottom
    }

    /**
     * Place children vertically such that each two adjacent ones are spaced by a fixed [space]
     * distance. The spacing will be subtracted from the available height that the children
     * can occupy. An [alignment] can be specified to align the spaced children vertically
     * inside the parent, in case there is empty height remaining. The [space] can be negative,
     * in which case children will overlap.
     *
     * @param space The space between adjacent children.
     * @param alignment The alignment of the spaced children inside the parent.
     */
    @Serializable
    @SerialName("arrangement@spaced_by_alignment_vertical")
    data class SpacedByAlignmentVertical(
        val space: DimensionDp,
        val alignment: SAlignmentVertical
    ) : SArrangementVertical {
        override fun asCompose(): Arrangement.Vertical =
            Arrangement.spacedBy(
                space = space.asCompose(),
                alignment = alignment.asCompose()
            )
    }


    /**
     * Place children vertically one next to the other and align the obtained group
     * according to an [alignment].
     *
     * @param alignment The alignment of the children inside the parent.
     */
    @Serializable
    @SerialName("arrangement@aligned_vertically")
    data class AlignedVertically(
        val alignment: SAlignmentVertical
    ) : SArrangementVertical {
        override fun asCompose(): Arrangement.Vertical =
            Arrangement.aligned(
                alignment = alignment.asCompose()
            )
    }
}
