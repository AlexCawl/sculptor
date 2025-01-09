package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
abstract class SkulptorLayout {
    abstract val id: String

    abstract val modifiers: List<SkulptorModifier>

    abstract fun Scope.build(): @Composable () -> Unit

    internal fun internalBuild(scope: Scope): @Composable () -> Unit =
        with(scope) { build() }

    interface Scope : SkulptorScope, SkulptorDispatcher {
        fun carve(modifiers: List<SkulptorModifier>): Modifier

        @Composable
        fun Any.place(layout: SkulptorLayout)
    }
}
