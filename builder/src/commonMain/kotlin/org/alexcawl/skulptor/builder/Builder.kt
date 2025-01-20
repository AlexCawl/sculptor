package org.alexcawl.skulptor.builder

import org.alexcawl.skulptor.core.state.BaseState
import org.alexcawl.skulptor.core.factory.BaseLayout

interface Builder {
    val layouts: List<BaseLayout<out BaseState>>
    val states: List<BaseState>

    fun addState(state: BaseState)

    fun addLayout(layout: BaseLayout<out BaseState>)
}

class BuilderImpl : Builder {
    private val _layouts: MutableList<BaseLayout<out BaseState>> = mutableListOf()
    override val layouts: List<BaseLayout<out BaseState>>
        get() = _layouts.toList()

    private val _states: MutableList<BaseState> = mutableListOf()
    override val states: List<BaseState>
        get() = _states.toList()

    override fun addState(state: BaseState) {
        _states.add(state)
    }

    override fun addLayout(layout: BaseLayout<out BaseState>) {
        _layouts.add(layout)
    }
}