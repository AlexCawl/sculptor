package org.alexcawl.skulptor.core.factory

import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.layout.SolidLayoutFactory

@Serializable
abstract class SolidLayout<S : BaseState> : BaseLayout<S> {
    abstract override val factory: SolidLayoutFactory<S>
}
