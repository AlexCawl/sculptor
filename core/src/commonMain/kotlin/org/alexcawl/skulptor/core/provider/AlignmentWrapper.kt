package org.alexcawl.skulptor.core.provider

import androidx.compose.ui.Alignment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.Provider

object AlignmentWrapper {
    @Serializable
    sealed interface HorizontalAndVertical : Provider<Alignment> {
        @Serializable
        @SerialName("alignment@top_start")
        data object TopStart : HorizontalAndVertical {
            override fun invoke(): Alignment =
                Alignment.TopStart
        }

        @Serializable
        @SerialName("alignment@top_center")
        data object TopCenter : HorizontalAndVertical {
            override fun invoke(): Alignment =
                Alignment.TopCenter
        }

        @Serializable
        @SerialName("alignment@top_end")
        data object TopEnd : HorizontalAndVertical {
            override fun invoke(): Alignment =
                Alignment.TopEnd
        }

        @Serializable
        @SerialName("alignment@center_start")
        data object CenterStart : HorizontalAndVertical {
            override fun invoke(): Alignment =
                Alignment.CenterStart
        }

        @Serializable
        @SerialName("alignment@center")
        data object Center : HorizontalAndVertical {
            override fun invoke(): Alignment =
                Alignment.Center
        }

        @Serializable
        @SerialName("alignment@center_end")
        data object CenterEnd : HorizontalAndVertical {
            override fun invoke(): Alignment =
                Alignment.CenterEnd
        }

        @Serializable
        @SerialName("alignment@bottom_start")
        data object BottomStart : HorizontalAndVertical {
            override fun invoke(): Alignment =
                Alignment.BottomStart
        }

        @Serializable
        @SerialName("alignment@bottom_center")
        data object BottomCenter : HorizontalAndVertical {
            override fun invoke(): Alignment =
                Alignment.BottomCenter
        }

        @Serializable
        @SerialName("alignment@bottom_end")
        data object BottomEnd : HorizontalAndVertical {
            override fun invoke(): Alignment =
                Alignment.BottomEnd
        }
    }

    @Serializable
    sealed interface Horizontal : Provider<Alignment.Horizontal> {
        @Serializable
        @SerialName("alignment@start")
        data object Start : Horizontal {
            override fun invoke(): Alignment.Horizontal =
                Alignment.Start
        }

        @Serializable
        @SerialName("alignment@center_horizontally")
        data object CenterHorizontally : Horizontal {
            override fun invoke(): Alignment.Horizontal =
                Alignment.CenterHorizontally
        }

        @Serializable
        @SerialName("alignment@end")
        data object End : Horizontal {
            override fun invoke(): Alignment.Horizontal =
                Alignment.End
        }
    }

    @Serializable
    sealed interface Vertical : Provider<Alignment.Vertical> {
        @Serializable
        @SerialName("alignment@top")
        data object Top : Vertical {
            override fun invoke(): Alignment.Vertical =
                Alignment.Top
        }

        @Serializable
        @SerialName("alignment@center_vertically")
        data object CenterVertically : Vertical {
            override fun invoke(): Alignment.Vertical =
                Alignment.CenterVertically
        }

        @Serializable
        @SerialName("alignment@bottom")
        data object Bottom : Vertical {
            override fun invoke(): Alignment.Vertical =
                Alignment.Bottom
        }
    }
}
