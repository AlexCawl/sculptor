package org.alexcawl.skulptor.core.dimension

import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorProperty

@Serializable
sealed interface SkulptorDimension<ComposeDimension> : SkulptorProperty<ComposeDimension> {
    val value: Number
}
