package org.alexcawl.skulptor.builder

import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.core.factory.BaseLayout
import org.alexcawl.skulptor.core.state.BaseState
import org.alexcawl.skulptor.foundation.layout.row.RowLayout
import org.alexcawl.skulptor.foundation.layout.row.RowState

inline fun Builder.row(state: RowState, modifiers: List<BaseModifier> = listOf(), block: Builder.() -> Unit) {
    val builder = BuilderImpl()
    builder.block()

    addState(state.copy(content = state.content + builder.layouts.map(BaseLayout<out BaseState>::id)))
    builder.states.forEach(::addState)

    addLayout(RowLayout(state.id, modifiers))
    builder.layouts.forEach(::addLayout)
}