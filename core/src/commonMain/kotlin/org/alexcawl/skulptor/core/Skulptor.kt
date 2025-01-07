package org.alexcawl.skulptor.core

import androidx.compose.ui.Modifier

interface Skulptor {
    val nodeId: String

    fun dispatch(action: SkulptorAction)

    fun carve(modifiers: List<SkulptorModifier>, scope: Any = Any()): Modifier

    fun place(layout: SkulptorLayout, scope: Any)
}
