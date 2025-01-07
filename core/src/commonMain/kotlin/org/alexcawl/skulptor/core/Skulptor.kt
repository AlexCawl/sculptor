package org.alexcawl.skulptor.core

import androidx.compose.ui.Modifier

interface Skulptor {
    val nodeId: String

    fun dispatch(action: SkulptorAction)

    fun carve(scope: Any, modifiers: List<SkulptorModifier>): Modifier

    fun place(scope: Any, layout: SkulptorLayout)
}
