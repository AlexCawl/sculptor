package org.alexcawl.skulptor.core.factory

import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.layout.BaseLayoutFactory

sealed interface BaseLayout<S : BaseState> {
    val id: String
    val modifiers: List<SkulptorModifier>
    val factory: BaseLayoutFactory<S>
}
