package org.alexcawl.skulptor.builder

import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.foundation.layout.basictext.BasicTextLayout
import org.alexcawl.skulptor.foundation.layout.basictext.BasicTextState

fun Builder.basicText(state: BasicTextState, modifiers: List<BaseModifier> = listOf()) {
    addState(state)
    addLayout(BasicTextLayout(state.id, modifiers))
}
