package org.alexcawl.skulptor.modifier

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.provider.ColorProvider
import org.alexcawl.skulptor.provider.ShapeProvider

sealed class BackgroundModifier : BaseModifier() {
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
