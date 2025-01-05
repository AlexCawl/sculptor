package org.alexcawl.skulptor.core.modifier.size

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.alignment.SAlignmentVertical
import org.alexcawl.skulptor.core.dimension.SDimensionDp
import org.alexcawl.skulptor.core.modifier.SkulptorModifier

@Serializable
sealed interface HeightModifier : SkulptorModifier {
    @Serializable
    @SerialName("modifier@height")
    data class Height(
        val value: SDimensionDp
    ) : HeightModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.height(
                height = value.asCompose()
            )
    }

    @Serializable
    @SerialName("modifier@height_in")
    data class HeightIn(
        @SerialName("min")
        val min: SDimensionDp,

        @SerialName("max")
        val max: SDimensionDp,
    ) : HeightModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.heightIn(
                min = min.asCompose(),
                max = max.asCompose()
            )
    }

    @Serializable
    @SerialName("modifier@required_height")
    data class RequiredHeight(
        val value: SDimensionDp
    ) : HeightModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.requiredHeight(
                height = value.asCompose()
            )
    }

    @Serializable
    @SerialName("modifier@required_height_in")
    data class RequiredHeightIn(
        @SerialName("min")
        val min: SDimensionDp,

        @SerialName("max")
        val max: SDimensionDp,
    ) : HeightModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.requiredHeightIn(
                min = min.asCompose(),
                max = max.asCompose()
            )
    }

    @Serializable
    @SerialName("modifier@height_fill_max")
    data class HeightFillMax(
        val fraction: Float
    ) : HeightModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.fillMaxHeight(
                fraction = fraction
            )
    }

    @Serializable
    @SerialName("modifier@height_wrap_content")
    data class HeightWrapContent(
        val alignment: SAlignmentVertical,
        val unbounded: Boolean,
    ) : HeightModifier {
        @Composable
        override fun asCompose(): Modifier =
            Modifier.wrapContentHeight(
                align = alignment.asCompose(),
                unbounded = unbounded
            )
    }
}