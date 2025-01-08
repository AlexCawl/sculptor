package org.alexcawl.skulptor.core.modifier

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.attribute.ColorSerializable
import org.alexcawl.skulptor.core.attribute.ShapeSerializable

sealed interface BackgroundModifier : SkulptorModifier {
    @Serializable
    @SerialName("modifier@background")
    data class Background(
        val color: ColorSerializable,
        val shape: ShapeSerializable
    ) : BackgroundModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.background(
                color = color,
                shape = shape
            )
    }
}
