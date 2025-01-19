@file:Suppress("UNCHECKED_CAST")

package org.alexcawl.skulptor.core.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastFold
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.BaseModifier

sealed class BaseLayoutFactory<S : BaseState> {
    @Composable
    internal inline fun build(
        layoutId: String,
        stateProvider: () -> BaseState,
        modifiers: List<BaseModifier>,
        initial: Modifier = Modifier,
    ) = build(
        state = stateProvider() as S,
        modifier = initial.chain(
            modifiers = modifiers,
            scope = BaseModifier.Scope(
                id = layoutId,
                scope = Skulptor.scope,
                dispatch = Skulptor.dispatch
            ),
        )
    )

    @Composable
    abstract fun build(state: S, modifier: Modifier)

    private fun Modifier.chain(
        modifiers: List<BaseModifier>,
        scope: BaseModifier.Scope,
    ): Modifier = modifiers.fastFold(initial = this) { modifier, skulptorModifier ->
        skulptorModifier.internalChain(initial = modifier, scope = scope)
    }
}
