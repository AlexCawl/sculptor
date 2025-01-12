package org.alexcawl.skulptor.core

import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.common.Scoped
import org.alexcawl.skulptor.core.common.Dispatcher

@Serializable
abstract class SkulptorModifier {
    abstract fun ModifierScope.chain(initial: Modifier): Modifier

    internal fun internalChain(initial: Modifier, scope: ModifierScope): Modifier =
        with(scope) { chain(initial) }

    interface ModifierScope : Scoped, Dispatcher {
        val id: String
    }
}
