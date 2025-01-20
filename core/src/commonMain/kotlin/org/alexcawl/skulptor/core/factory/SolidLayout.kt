package org.alexcawl.skulptor.core.factory

import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.state.BaseState
import org.alexcawl.skulptor.core.layout.SolidLayoutFactory
import org.alexcawl.skulptor.core.state.SolidState

@Serializable
abstract class SolidLayout<S : SolidState> : BaseLayout<S> {
    abstract override val factory: SolidLayoutFactory<S>
}
