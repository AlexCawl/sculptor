package org.alexcawl.skulptor.core.modifier

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.provider.ColorProvider
import org.alexcawl.skulptor.core.provider.ShapeProvider

sealed class BackgroundModifier : SkulptorModifier() {
    @Serializable
    @SerialName("background@color")
    data class Background(
        val color: ColorProvider,
        val shape: ShapeProvider
    ) : BackgroundModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.background(color = color(), shape = shape())
    }
}
