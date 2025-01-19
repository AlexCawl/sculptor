package org.alexcawl.skulptor.core.factory

import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.layout.CompositeLayoutFactory

@Serializable
abstract class CompositeLayout<S : BaseState> : BaseLayout<S> {
    abstract override val factory: CompositeLayoutFactory<S>
}
