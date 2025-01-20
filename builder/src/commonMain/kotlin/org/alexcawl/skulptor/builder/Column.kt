package org.alexcawl.skulptor.builder

import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.core.state.BaseState
import org.alexcawl.skulptor.core.factory.BaseLayout
import org.alexcawl.skulptor.foundation.layout.column.ColumnLayout
import org.alexcawl.skulptor.foundation.layout.column.ColumnState

inline fun Builder.column(state: ColumnState, modifiers: List<BaseModifier> = listOf(), block: Builder.() -> Unit) {
    val builder = BuilderImpl()
    builder.block()

    addState(state.copy(content = state.content + builder.layouts.map(BaseLayout<out BaseState>::id)))
    builder.states.forEach(::addState)

    addLayout(ColumnLayout(state.id, modifiers))
    builder.layouts.forEach(::addLayout)
}