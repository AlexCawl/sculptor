package org.alexcawl.skulptor.core.factory

import org.alexcawl.skulptor.core.state.BaseState
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.core.layout.BaseLayoutFactory

sealed interface BaseLayout<S : BaseState> {
    val id: String
    val modifiers: List<BaseModifier>
    val factory: BaseLayoutFactory<S>
}
