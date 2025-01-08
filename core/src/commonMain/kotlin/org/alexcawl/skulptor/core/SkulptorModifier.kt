package org.alexcawl.skulptor.core

import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
abstract class SkulptorModifier {
    abstract fun Scope.chain(initial: Modifier): Modifier

    interface Scope {
        val id: String

        val scope: Any

        fun dispatch(action: SkulptorAction)
    }
}
