package org.alexcawl.skulptor.core.factory

import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.layout.ComponentLayoutFactory

@Serializable
abstract class ComponentLayout<S : BaseState> : BaseLayout<S> {
    abstract override val factory: ComponentLayoutFactory<S>
}
