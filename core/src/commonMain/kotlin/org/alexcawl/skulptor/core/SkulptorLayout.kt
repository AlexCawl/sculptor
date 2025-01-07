package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface SkulptorLayout {
    val id: String

    val modifier: List<SkulptorModifier>

    val state: Any

    @Composable
    fun buildLayout(
        scope: Any,
        modifier: Modifier,
        onChild: (child: SkulptorLayout, parentScope: Any) -> Unit
    ): @Composable () -> Unit
}
