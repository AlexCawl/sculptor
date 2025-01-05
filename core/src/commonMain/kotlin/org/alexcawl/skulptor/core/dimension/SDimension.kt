package org.alexcawl.skulptor.core.dimension

import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SAttribute

@Serializable
sealed interface SDimension<ComposeDimension> : SAttribute<ComposeDimension> {
    val value: Number
}
