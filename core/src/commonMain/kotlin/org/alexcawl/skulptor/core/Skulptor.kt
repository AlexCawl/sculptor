package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastFold

interface Skulptor : SkulptorModifier.Scope, SkulptorLayout.Scope {
    @Composable
    fun place(layout: SkulptorLayout)

    companion object {
        fun root(scope: Any = Any()): Skulptor = SkulptorImpl(id = "ROOT", scope = scope)
    }
}

private class SkulptorImpl(
    override val id: String,
    override val scope: Any
) : Skulptor {
    override fun carve(modifiers: List<SkulptorModifier>): Modifier {
        val initial: Modifier = Modifier
        return modifiers.fastFold(initial) { modifier, skulptorModifier ->
            skulptorModifier.internalChain(initial = modifier, scope = this)
        }
    }

    @Composable
    override fun place(layout: SkulptorLayout) = with(scope) { this.place(layout) }

    @Composable
    override fun Any.place(layout: SkulptorLayout) {
        val skulptor = SkulptorImpl(id = layout.id, scope = this)
        layout.internalBuild(skulptor).invoke()
    }

    override fun dispatch(action: SkulptorAction) {
        println("$id: $action")
    }
}
