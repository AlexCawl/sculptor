@file:Suppress("UNCHECKED_CAST")

package org.alexcawl.skulptor.core.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastFold
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.SkulptorAction
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.dispatchProvider
import org.alexcawl.skulptor.core.scopeProvider

sealed class BaseLayoutFactory<S : BaseState> {
    @Composable
    internal operator fun invoke(
        layoutId: String,
        stateProvider: () -> BaseState,
        modifiers: List<SkulptorModifier>,
        initial: Modifier = Modifier,
    ): @Composable () -> Unit {
        val scope = scope
        val dispatch = dispatcher
        return build(
            state = stateProvider() as S,
            modifier = carve(
                modifiers = modifiers,
                scope = object : SkulptorModifier.ModifierScope {
                    override val id: String = layoutId
                    override val scope: Any = scope
                    override fun dispatch(action: SkulptorAction) = dispatch(action)
                },
                initial = initial
            )
        )
    }

    abstract fun build(state: S, modifier: Modifier): @Composable () -> Unit

    protected val scope: Any
        @Composable get() = scopeProvider.current

    protected val dispatcher: (SkulptorAction) -> Unit
        @Composable get() = dispatchProvider.current

    private fun carve(
        modifiers: List<SkulptorModifier>,
        scope: SkulptorModifier.ModifierScope,
        initial: Modifier = Modifier
    ): Modifier = modifiers.fastFold(initial) { modifier, skulptorModifier ->
        skulptorModifier.internalChain(initial = modifier, scope = scope)
    }
}
