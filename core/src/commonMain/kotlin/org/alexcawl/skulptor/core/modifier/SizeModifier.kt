package org.alexcawl.skulptor.core.modifier

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.provider.AlignmentProvider
import org.alexcawl.skulptor.core.provider.DpSizeProvider

sealed interface SizeModifier : SkulptorModifier {
    @Serializable
    @SerialName("modifier@size")
    data class Size(
        @SerialName("size")
        val size: DpSizeProvider
    ) : SizeModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.size(size = size())
    }

    @Serializable
    @SerialName("modifier@size_in")
    data class SizeIn(
        @SerialName("min")
        val min: DpSizeProvider,
        @SerialName("max")
        val max: DpSizeProvider,
    ) : SizeModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier {
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

    @SerialName("modifier@required_size")
    data class RequiredSize(
        @SerialName("size")
        val size: DpSizeProvider
    ) : SizeModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.requiredSize(size = size())
    }

    @Serializable
    @SerialName("modifier@required_size_in")
    data class RequiredSizeIn(
        @SerialName("min")
        val min: DpSizeProvider,
        @SerialName("max")
        val max: DpSizeProvider,
    ) : SizeModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier {
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
    @SerialName("modifier@fill_max_size")
    data class FillMaxSize(
        @SerialName("fraction")
        val fraction: Float
    ) : SizeModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.fillMaxSize(fraction = fraction)
    }

    @Serializable
    @SerialName("modifier@wrap_content_size")
    data class WrapContentSize(
        @SerialName("align")
        val align: AlignmentProvider.HorizontalAndVertical,
        @SerialName("unbounded")
        val unbounded: Boolean,
    ) : SizeModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.wrapContentSize(
                align = align(),
                unbounded = unbounded
            )
    }
}
