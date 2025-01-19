package org.alexcawl.skulptor.core.factory

import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.layout.ContainerLayoutFactory

@Serializable
abstract class ContainerLayout<S : BaseState> : BaseLayout<S> {
    abstract override val factory: ContainerLayoutFactory<S>
}
