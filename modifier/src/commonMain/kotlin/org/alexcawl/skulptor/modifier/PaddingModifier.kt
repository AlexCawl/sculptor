package org.alexcawl.skulptor.modifier

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.provider.DpProvider

@Serializable
sealed class PaddingModifier : BaseModifier() {
    @Serializable
    data class Padding(
        val start: DpProvider,
        val top: DpProvider,
        val end: DpProvider,
        val bottom: DpProvider,
    ) : PaddingModifier() {
        constructor(all: DpProvider) : this(all, all, all, all)

        constructor(horizontal: DpProvider, vertical: DpProvider) : this(horizontal, vertical, horizontal, vertical)

        override fun Scope.chain(initial: Modifier): Modifier = initial.padding(
            start = start(),
            top = top(),
            end = end(),
            bottom = bottom()
        )
    }
}