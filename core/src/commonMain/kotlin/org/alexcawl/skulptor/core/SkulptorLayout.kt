package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
abstract class SkulptorLayout {
    abstract val id: String

    abstract val modifiers: List<SkulptorModifier>

    abstract fun Scope.build(): @Composable () -> Unit

    interface Scope {
        val scope: Any

        fun dispatch(action: SkulptorAction)

        fun carve(modifiers: List<SkulptorModifier>): Modifier

        @Composable
        fun Any.place(layout: SkulptorLayout)
    }
}
