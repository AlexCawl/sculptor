package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface SkulptorLayout {
    val id: String

    val modifiers: List<SkulptorModifier>

    val state: Any

    @Composable
    fun Skulptor.build(scope: Any): @Composable () -> Unit
}
