package org.alexcawl.skulptor.core

import androidx.compose.ui.Modifier
import org.alexcawl.skulptor.core.common.Dispatcher
import org.alexcawl.skulptor.core.common.Scoped

sealed interface BaseLayout {
    val id: String
    val modifiers: List<SkulptorModifier>

    interface BaseLayoutScope : Scoped, Dispatcher {
        fun carve(modifiers: List<SkulptorModifier>): Modifier

        fun <T : BaseState> getState(id: String): T

        fun <T : BaseState> getStateOrNull(id: String): T?
    }
}
