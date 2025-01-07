package org.alexcawl.skulptor.core.attribute

import androidx.compose.foundation.layout.Arrangement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAttribute

object ArrangementWrapper {
    /**
     * Used to specify the horizontal arrangement of the layout's children in horizontal layouts
     * like [androidx.compose.foundation.layout.Row], or the vertical arrangement of the layout's children in vertical layouts like
     * [androidx.compose.foundation.layout.Column].
     */
    @Serializable
    sealed interface HorizontalOrVertical : SkulptorAttribute<Arrangement.HorizontalOrVertical> {
        /**
         * Place children such that they are as close as possible to the middle of the main axis.
         * Visually: ##123## for LTR and ##321## for RTL.
         */
        @Serializable
        @SerialName("arrangement@center")
        data object Center : HorizontalOrVertical {
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
        data object SpaceEvenly : HorizontalOrVertical {
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
        data object SpaceBetween : HorizontalOrVertical {
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
        data object SpaceAround : HorizontalOrVertical {
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
            val space: DpSerializable
        ) : HorizontalOrVertical {
            override fun asCompose(): Arrangement.HorizontalOrVertical =
                Arrangement.spacedBy(space)
        }
    }

    /**
     * Used to specify the horizontal arrangement of the layout's children in layouts like [androidx.compose.foundation.layout.Row].
     */
    @Serializable
    sealed interface Horizontal : SkulptorAttribute<Arrangement.Horizontal> {
        /**
         * Place children horizontally such that they are as close as possible to the beginning of the
         * horizontal axis (left if the layout direction is LTR, right otherwise).
         * Visually: 123#### for LTR and ####321.
         */
        @Serializable
        @SerialName("arrangement@start")
        data object Start : Horizontal {
            override fun asCompose(): Arrangement.Horizontal =
                Arrangement.Start
        }

        /**
         * Place children horizontally such that they are as close as possible to the end of the main
         * axis.
         * Visually: ####123 for LTR and 321#### for RTL.
         */
        @Serializable
        @SerialName("arrangement@end")
        data object End : Horizontal {
            override fun asCompose(): Arrangement.Horizontal =
                Arrangement.End
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
            val space: DpSerializable,
            val alignment: AlignmentWrapper.Horizontal
        ) : Horizontal {
            override fun asCompose(): Arrangement.Horizontal =
                Arrangement.spacedBy(
                    space = space,
                    alignment = alignment.asCompose()
                )
        }

        /**
         * Place children horizontally one next to the other and align the obtained group
         * according to an [alignment].
         *
         * @param alignment The alignment of the children inside the parent.
         */
        @Serializable
        @SerialName("arrangement@aligned_horizontally")
        data class AlignedHorizontally(
            val alignment: AlignmentWrapper.Horizontal
        ) : Horizontal {
            override fun asCompose(): Arrangement.Horizontal =
                Arrangement.aligned(
                    alignment = alignment.asCompose()
                )
        }
    }

    /**
     * Used to specify the vertical arrangement of the layout's children in layouts like [androidx.compose.foundation.layout.Column].
     */
    @Serializable
    sealed interface Vertical : SkulptorAttribute<Arrangement.Vertical> {
        /**
         * Place children vertically such that they are as close as possible to the top of the main
         * axis.
         * Visually: (top) 123#### (bottom)
         */
        @Serializable
        @SerialName("arrangement@top")
        data object Top : Vertical {
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
        data object Bottom : Vertical {
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
            val space: DpSerializable,
            val alignment: AlignmentWrapper.Vertical
        ) : Vertical {
            override fun asCompose(): Arrangement.Vertical =
                Arrangement.spacedBy(
                    space = space,
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
            val alignment: AlignmentWrapper.Vertical
        ) : Vertical {
            override fun asCompose(): Arrangement.Vertical =
                Arrangement.aligned(
                    alignment = alignment.asCompose()
                )
        }
    }
}
