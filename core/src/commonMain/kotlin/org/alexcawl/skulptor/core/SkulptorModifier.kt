package org.alexcawl.skulptor.core

import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.common.Scoped
import org.alexcawl.skulptor.core.common.Dispatcher

@Serializable
abstract class SkulptorModifier {
    abstract fun Scope.chain(initial: Modifier): Modifier

    internal fun internalChain(initial: Modifier, scope: Scope): Modifier =
        with(scope) { chain(initial) }

    interface Scope : Scoped, Dispatcher {
        val id: String
    }
}
