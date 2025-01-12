package org.alexcawl.skulptor.core

import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
abstract class SkulptorModifier {
    abstract fun ModifierScope.chain(initial: Modifier): Modifier

    internal fun internalChain(initial: Modifier, scope: ModifierScope): Modifier =
        with(scope) { chain(initial) }

    interface ModifierScope {
        val id: String

        val scope: Any

        fun dispatch(action: SkulptorAction)
    }
}
