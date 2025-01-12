package org.alexcawl.skulptor.modifier

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.provider.AlignmentProvider
import org.alexcawl.skulptor.provider.DpProvider

@Serializable
sealed class HeightModifier : SkulptorModifier() {
    @Serializable
    @SerialName("height@height")
    data class Height(val height: DpProvider) : HeightModifier() {
        override fun ModifierScope.chain(initial: Modifier): Modifier =
            initial.height(height = height())
    }

    @Serializable
    @SerialName("height@height_in")
    data class HeightIn(val min: DpProvider, val max: DpProvider) : HeightModifier() {
        override fun ModifierScope.chain(initial: Modifier): Modifier =
            initial.heightIn(min = min(), max = max())
    }

    @Serializable
    @SerialName("height@required_height")
    data class RequiredHeight(val height: DpProvider) : HeightModifier() {
        override fun ModifierScope.chain(initial: Modifier): Modifier =
            initial.requiredHeight(height = height())
    }

    @Serializable
    @SerialName("height@required_height_in")
    data class RequiredHeightIn(val min: DpProvider, val max: DpProvider) : HeightModifier() {
        override fun ModifierScope.chain(initial: Modifier): Modifier =
            initial.requiredHeightIn(min = min(), max = max())
    }

    @Serializable
    @SerialName("height@fill_max_height")
    data class FillMaxHeight(val fraction: Float) : HeightModifier() {
        override fun ModifierScope.chain(initial: Modifier): Modifier =
            initial.fillMaxHeight(fraction = fraction)
    }

    @Serializable
    @SerialName("height@wrap_content_height")
    data class WrapContentHeight(
        val align: AlignmentProvider.Vertical,
        val unbounded: Boolean,
    ) : HeightModifier() {
        override fun ModifierScope.chain(initial: Modifier): Modifier =
            initial.wrapContentHeight(align = align(), unbounded = unbounded)
    }
}
