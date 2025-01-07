package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable

interface SkulptorLayout {
    val id: String

    val modifiers: List<SkulptorModifier>

    val state: Any

    fun build(skulptor: Skulptor, scope: Any): @Composable () -> Unit
}
