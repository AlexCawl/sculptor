package org.alexcawl.skulptor.core

import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
abstract class BaseModifier {
    abstract fun Scope.chain(initial: Modifier): Modifier

    internal fun internalChain(initial: Modifier, scope: Scope): Modifier =
        with(scope) { chain(initial) }

    data class Scope(
        val id: String,
        val scope: Any,
        val dispatch: (BaseAction) -> Unit,
    )
}
