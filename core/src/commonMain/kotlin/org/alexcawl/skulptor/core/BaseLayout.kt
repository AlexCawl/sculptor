package org.alexcawl.skulptor.core

import androidx.compose.ui.Modifier

sealed interface BaseLayout<S : BaseState> {
    val id: String
    val modifiers: List<SkulptorModifier>

    interface BaseLayoutScope {
        val scope: Any

        fun dispatch(action: SkulptorAction)

        fun carve(modifiers: List<SkulptorModifier>): Modifier

        fun <T : BaseState> getState(id: String): T

        fun <T : BaseState> getStateOrNull(id: String): T?
    }
}
