package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastFold

interface Skulptor {
    val layoutId: String
        @Composable
        get

    fun dispatch(action: SkulptorAction)

    fun carve(modifiers: List<SkulptorModifier>, scope: Any = Any()): Modifier

    @Composable
    fun place(layout: SkulptorLayout, scope: Any)
}

private class SkulptorImpl : Skulptor {
    override val layoutId: String
        @Composable
        get() = layoutIdProvider.current

    override fun dispatch(action: SkulptorAction) {

    }

    override fun carve(modifiers: List<SkulptorModifier>, scope: Any): Modifier {
        val initialModifier: Modifier = Modifier
        return modifiers.fastFold(initialModifier) { m, sm ->
            sm.chain(initial = m, skulptor = this, scope = scope)
        }
    }

    @Composable
    override fun place(layout: SkulptorLayout, scope: Any) {
        CompositionLocalProvider(layoutIdProvider provides layout.id) {
            layout.build(
                skulptor = this,
                scope = scope
            ).invoke()
        }
    }

    companion object {
        internal val layoutIdProvider: ProvidableCompositionLocal<String> =
            staticCompositionLocalOf { "ROOT" }
    }
}