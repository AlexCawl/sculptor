package org.alexcawl.skulptor.core.modifier

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.provider.AlignmentProvider
import org.alexcawl.skulptor.core.provider.DpProvider

sealed interface WidthModifier : SkulptorModifier {
    @Serializable
    @SerialName("modifier@width")
    data class Width(
        @SerialName("width")
        val width: DpProvider
    ) : WidthModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.width(width = width())
    }

    @Serializable
    @SerialName("modifier@width_in")
    data class WidthIn(
        @SerialName("min")
        val min: DpProvider,
        @SerialName("max")
        val max: DpProvider,
    ) : WidthModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.widthIn(
                min = min(),
                max = max(),
            )
    }

    @Serializable
    @SerialName("modifier@required_width")
    data class RequiredWidth(
        @SerialName("width")
        val width: DpProvider
    ) : WidthModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.requiredWidth(width = width())
    }

    @Serializable
    @SerialName("modifier@required_width_in")
    data class RequiredWidthIn(
        @SerialName("min")
        val min: DpProvider,
        @SerialName("max")
        val max: DpProvider,
    ) : WidthModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.requiredWidthIn(
                min = min(),
                max = max(),
            )
    }

    @Serializable
    @SerialName("modifier@fill_max_width")
    data class FillMaxWidth(
        @SerialName("fraction")
        val fraction: Float
    ) : WidthModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.fillMaxWidth(fraction = fraction)
    }

    @Serializable
    @SerialName("modifier@wrap_content_width")
    data class WrapContentWidth(
        @SerialName("align")
        val align: AlignmentProvider.Horizontal,
        @SerialName("unbounded")
        val unbounded: Boolean,
    ) : WidthModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.wrapContentWidth(
                align = align(),
                unbounded = unbounded
            )
    }
}
