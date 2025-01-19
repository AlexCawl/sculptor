package org.alexcawl.skulptor.modifier

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.provider.AlignmentProvider
import org.alexcawl.skulptor.provider.DpSizeProvider

@Serializable
sealed class SizeModifier : BaseModifier() {
    @Serializable
    @SerialName("size@size")
    data class Size(val size: DpSizeProvider) : SizeModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.size(size = size())
    }

    @Serializable
    @SerialName("size@size_in")
    data class SizeIn(val min: DpSizeProvider, val max: DpSizeProvider) : SizeModifier() {
        override fun Scope.chain(initial: Modifier): Modifier {
            val (minWidth, minHeight) = min()
            val (maxWidth, maxHeight) = max()
            return initial.sizeIn(
                minWidth = minWidth,
                minHeight = minHeight,
                maxWidth = maxWidth,
                maxHeight = maxHeight
            )
        }
    }

    @SerialName("size@required_size")
    data class RequiredSize(val size: DpSizeProvider) : SizeModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.requiredSize(size = size())
    }

    @Serializable
    @SerialName("size@required_size_in")
    data class RequiredSizeIn(val min: DpSizeProvider, val max: DpSizeProvider) : SizeModifier() {
        override fun Scope.chain(initial: Modifier): Modifier {
            val (minWidth, minHeight) = min()
            val (maxWidth, maxHeight) = max()
            return initial.requiredSizeIn(
                minWidth = minWidth,
                minHeight = minHeight,
                maxWidth = maxWidth,
                maxHeight = maxHeight
            )
        }
    }

    @Serializable
    @SerialName("size@fill_max_size")
    data class FillMaxSize(val fraction: Float) : SizeModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.fillMaxSize(fraction = fraction)
    }

    @Serializable
    @SerialName("size@wrap_content_size")
    data class WrapContentSize(
        val align: AlignmentProvider.HorizontalAndVertical,
        val unbounded: Boolean,
    ) : SizeModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.wrapContentSize(align = align(), unbounded = unbounded)
    }
}
