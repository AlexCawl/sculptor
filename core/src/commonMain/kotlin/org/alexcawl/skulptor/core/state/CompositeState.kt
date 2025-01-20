package org.alexcawl.skulptor.core.state

abstract class CompositeState : BaseState {
    abstract val content: List<String>
}
