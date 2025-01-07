package org.alexcawl.skulptor.core.modifier.background

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.attribute.ColorWrapper
import org.alexcawl.skulptor.core.attribute.ShapeWrapper

@Serializable
sealed interface BackgroundModifier : SkulptorModifier {
    data class Background(
        @SerialName("color")
        val color: ColorWrapper,
        @SerialName("shape")
        val shape: ShapeWrapper
    ) : BackgroundModifier {
        @Composable
        override fun asCompose(layout: SkulptorLayout): Modifier =
            Modifier.background(
                color = color.asCompose(),
                shape = shape.asCompose()
            )
    }
}
