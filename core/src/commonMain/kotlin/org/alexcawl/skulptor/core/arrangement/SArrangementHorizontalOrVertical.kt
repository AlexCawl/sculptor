package org.alexcawl.skulptor.core.arrangement

import androidx.compose.foundation.layout.Arrangement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAttribute
import org.alexcawl.skulptor.core.dimension.SDimensionDp

/**
 * Used to specify the horizontal arrangement of the layout's children in horizontal layouts
 * like [androidx.compose.foundation.layout.Row], or the vertical arrangement of the layout's children in vertical layouts like
 * [androidx.compose.foundation.layout.Column].
 */
@Serializable
sealed interface SArrangementHorizontalOrVertical : SkulptorAttribute<Arrangement.HorizontalOrVertical> {
    /**
     * Place children such that they are as close as possible to the middle of the main axis.
     * Visually: ##123## for LTR and ##321## for RTL.
     */
    @Serializable
    @SerialName("arrangement@center")
    data object Center : SArrangementHorizontalOrVertical {
        override fun asCompose(): Arrangement.HorizontalOrVertical =
            Arrangement.Center
    }

    /**
     * Place children such that they are spaced evenly across the main axis, including free
     * space before the first child and after the last child.
     * Visually: #1#2#3# for LTR and #3#2#1# for RTL.
     */
    @Serializable
    @SerialName("arrangement@space_evenly")
    data object SpaceEvenly : SArrangementHorizontalOrVertical {
        override fun asCompose(): Arrangement.HorizontalOrVertical =
            Arrangement.SpaceEvenly
    }

    /**
     * Place children such that they are spaced evenly across the main axis, without free
     * space before the first child or after the last child.
     * Visually: 1##2##3 for LTR or 3##2##1 for RTL.
     */
    @Serializable
    @SerialName("arrangement@space_between")
    data object SpaceBetween : SArrangementHorizontalOrVertical {
        override fun asCompose(): Arrangement.HorizontalOrVertical =
            Arrangement.SpaceBetween
    }

    /**
     * Place children such that they are spaced evenly across the main axis, including free
     * space before the first child and after the last child, but half the amount of space
     * existing otherwise between two consecutive children.
     * Visually: #1##2##3# for LTR and #3##2##1# for RTL
     */
    @Serializable
    @SerialName("arrangement@space_around")
    data object SpaceAround : SArrangementHorizontalOrVertical {
        override fun asCompose(): Arrangement.HorizontalOrVertical =
            Arrangement.SpaceAround
    }

    /**
     * Place children such that each two adjacent ones are spaced by a fixed [space] distance across
     * the main axis. The spacing will be subtracted from the available space that the children
     * can occupy. The [space] can be negative, in which case children will overlap.
     *
     * To change alignment of the spaced children horizontally or vertically, use [SpacedBy]
     * overloads with `alignment` parameter.
     *
     * @param space The space between adjacent children.
     */
    @Serializable
    @SerialName("arrangement@spaced_by")
    data class SpacedBy(
        val space: SDimensionDp
    ) : SArrangementHorizontalOrVertical {
        override fun asCompose(): Arrangement.HorizontalOrVertical =
            Arrangement.spacedBy(space.asCompose())
    }
}
