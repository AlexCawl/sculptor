package org.alexcawl.skulptor.modifier

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.provider.AlignmentProvider
import org.alexcawl.skulptor.provider.DpProvider

@Serializable
sealed class WidthModifier : BaseModifier() {
    @Serializable
    @SerialName("modifier@width")
    data class Width(val width: DpProvider) : WidthModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.width(width = width())
    }

    @Serializable
    @SerialName("modifier@width_in")
    data class WidthIn(val min: DpProvider, val max: DpProvider) : WidthModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.widthIn(min = min(), max = max())
    }

    @Serializable
    @SerialName("modifier@required_width")
    data class RequiredWidth(val width: DpProvider) : WidthModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.requiredWidth(width = width())
    }

    @Serializable
    @SerialName("modifier@required_width_in")
    data class RequiredWidthIn(val min: DpProvider, val max: DpProvider) : WidthModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.requiredWidthIn(min = min(), max = max())
    }

    @Serializable
    @SerialName("modifier@fill_max_width")
    data class FillMaxWidth(val fraction: Float) : WidthModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.fillMaxWidth(fraction = fraction)
    }

    @Serializable
    @SerialName("modifier@wrap_content_width")
    data class WrapContentWidth(
        val align: AlignmentProvider.Horizontal,
        val unbounded: Boolean,
    ) : WidthModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.wrapContentWidth(
                align = align(),
                unbounded = unbounded
            )
    }
}
