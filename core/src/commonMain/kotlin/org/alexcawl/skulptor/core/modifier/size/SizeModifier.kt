package org.alexcawl.skulptor.core.modifier.size

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.alignment.SAlignment
import org.alexcawl.skulptor.core.dimension.SDimensionDpSize
import org.alexcawl.skulptor.core.modifier.SkulptorModifier

@Serializable
sealed interface SizeModifier : SkulptorModifier {
    @Serializable
    @SerialName("modifier@size")
    data class Size(
        val value: SDimensionDpSize
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.size(
                size = value.asCompose()
            )
    }

    @Serializable
    @SerialName("modifier@size_in")
    data class SizeIn(
        @SerialName("min")
        val min: SDimensionDpSize,

        @SerialName("max")
        val max: SDimensionDpSize,
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier {
            val (minWidth, minHeight) = min.asCompose()
            val (maxWidth, maxHeight) = max.asCompose()
            return Modifier.sizeIn(
                minWidth = minWidth,
                minHeight = minHeight,
                maxWidth = maxWidth,
                maxHeight = maxHeight
            )
        }
    }

    @Serializable
    @SerialName("modifier@required_size")
    data class RequiredSize(
        val value: SDimensionDpSize
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.requiredSize(
                size = value.asCompose()
            )
    }

    @Serializable
    @SerialName("modifier@required_size_in")
    data class RequiredSizeIn(
        @SerialName("min")
        val min: SDimensionDpSize,

        @SerialName("max")
        val max: SDimensionDpSize,
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier {
            val (minWidth, minHeight) = min.asCompose()
            val (maxWidth, maxHeight) = max.asCompose()
            return Modifier.requiredSizeIn(
                minWidth = minWidth,
                minHeight = minHeight,
                maxWidth = maxWidth,
                maxHeight = maxHeight
            )
        }
    }

    @Serializable
    @SerialName("modifier@size_fill_max")
    data class SizeFillMax(
        val fraction: Float
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.fillMaxSize(
                fraction = fraction
            )
    }

    @Serializable
    @SerialName("modifier@size_wrap_content")
    data class SizeWrapContent(
        val alignment: SAlignment,
        val unbounded: Boolean,
    ) : SizeModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.wrapContentSize(
                align = alignment.asCompose(),
                unbounded = unbounded
            )
    }
}