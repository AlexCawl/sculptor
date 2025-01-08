package org.alexcawl.skulptor.core.modifier

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.provider.AlignmentProvider
import org.alexcawl.skulptor.core.provider.DpProvider

sealed interface HeightModifier : SkulptorModifier {
    @Serializable
    @SerialName("modifier@height")
    data class Height(
        @SerialName("height")
        val height: DpProvider
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.height(height = height())
    }

    @Serializable
    @SerialName("modifier@height_in")
    data class HeightIn(
        @SerialName("min")
        val min: DpProvider,
        @SerialName("max")
        val max: DpProvider,
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.heightIn(
                min = min(),
                max = max()
            )
    }

    @Serializable
    @SerialName("modifier@required_height")
    data class RequiredHeight(
        @SerialName("height")
        val height: DpProvider
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.requiredHeight(
                height = height()
            )
    }

    @Serializable
    @SerialName("modifier@required_height_in")
    data class RequiredHeightIn(
        @SerialName("min")
        val min: DpProvider,
        @SerialName("max")
        val max: DpProvider,
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.requiredHeightIn(
                min = min(),
                max = max()
            )
    }

    @Serializable
    @SerialName("modifier@fill_max_height")
    data class FillMaxHeight(
        @SerialName("fraction")
        val fraction: Float
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.fillMaxHeight(
                fraction = fraction
            )
    }

    @Serializable
    @SerialName("modifier@wrap_content_height")
    data class WrapContentHeight(
        @SerialName("align")
        val align: AlignmentProvider.Vertical,
        @SerialName("unbounded")
        val unbounded: Boolean,
    ) : HeightModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.wrapContentHeight(
                align = align(),
                unbounded = unbounded
            )
    }
}
