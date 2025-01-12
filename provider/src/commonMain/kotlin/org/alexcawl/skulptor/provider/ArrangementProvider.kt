package org.alexcawl.skulptor.provider

import androidx.compose.foundation.layout.Arrangement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object ArrangementProvider {
    @Serializable
    sealed interface HorizontalOrVertical : Provider<Arrangement.HorizontalOrVertical> {
        @Serializable
        @SerialName("center")
        data object Center : HorizontalOrVertical {
            override fun invoke(): Arrangement.HorizontalOrVertical = Arrangement.Center
        }

        @Serializable
        @SerialName("space_evenly")
        data object SpaceEvenly : HorizontalOrVertical {
            override fun invoke(): Arrangement.HorizontalOrVertical = Arrangement.SpaceEvenly
        }

        @Serializable
        @SerialName("space_between")
        data object SpaceBetween : HorizontalOrVertical {
            override fun invoke(): Arrangement.HorizontalOrVertical = Arrangement.SpaceBetween
        }

        @Serializable
        @SerialName("space_around")
        data object SpaceAround : HorizontalOrVertical {
            override fun invoke(): Arrangement.HorizontalOrVertical = Arrangement.SpaceAround
        }

        @Serializable
        @SerialName("spaced_by")
        data class SpacedBy(val space: DpProvider) : HorizontalOrVertical {
            override fun invoke(): Arrangement.HorizontalOrVertical = Arrangement.spacedBy(space())
        }
    }

    @Serializable
    sealed interface Horizontal : Provider<Arrangement.Horizontal> {
        @Serializable
        @SerialName("start")
        data object Start : Horizontal {
            override fun invoke(): Arrangement.Horizontal = Arrangement.Start
        }

        @Serializable
        @SerialName("end")
        data object End : Horizontal {
            override fun invoke(): Arrangement.Horizontal = Arrangement.End
        }

        @Serializable
        @SerialName("spaced_by_alignment_horizontal")
        data class SpacedByAlignmentHorizontal(
            val space: DpProvider,
            val alignment: AlignmentProvider.Horizontal
        ) : Horizontal {
            override fun invoke(): Arrangement.Horizontal = Arrangement.spacedBy(
                space = space(),
                alignment = alignment.invoke()
            )
        }

        @Serializable
        @SerialName("aligned_horizontally")
        data class AlignedHorizontally(val alignment: AlignmentProvider.Horizontal) : Horizontal {
            override fun invoke(): Arrangement.Horizontal = Arrangement.aligned(
                alignment = alignment.invoke()
            )
        }
    }

    @Serializable
    sealed interface Vertical : Provider<Arrangement.Vertical> {
        @Serializable
        @SerialName("top")
        data object Top : Vertical {
            override fun invoke(): Arrangement.Vertical = Arrangement.Top
        }

        @Serializable
        @SerialName("bottom")
        data object Bottom : Vertical {
            override fun invoke(): Arrangement.Vertical = Arrangement.Bottom
        }

        @Serializable
        @SerialName("spaced_by_alignment_vertical")
        data class SpacedByAlignmentVertical(
            val space: DpProvider,
            val alignment: AlignmentProvider.Vertical
        ) : Vertical {
            override fun invoke(): Arrangement.Vertical = Arrangement.spacedBy(
                space = space(),
                alignment = alignment.invoke()
            )
        }

        @Serializable
        @SerialName("aligned_vertically")
        data class AlignedVertically(val alignment: AlignmentProvider.Vertical) : Vertical {
            override fun invoke(): Arrangement.Vertical =
                Arrangement.aligned(alignment = alignment.invoke())
        }
    }
}
