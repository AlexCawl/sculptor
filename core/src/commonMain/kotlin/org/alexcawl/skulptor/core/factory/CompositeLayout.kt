package org.alexcawl.skulptor.core.factory

import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.state.BaseState
import org.alexcawl.skulptor.core.layout.CompositeLayoutFactory
import org.alexcawl.skulptor.core.state.CompositeState

@Serializable
abstract class CompositeLayout<S : CompositeState> : BaseLayout<S> {
    abstract override val factory: CompositeLayoutFactory<S>
}
