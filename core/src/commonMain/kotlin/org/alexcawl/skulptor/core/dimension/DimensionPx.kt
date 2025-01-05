package org.alexcawl.skulptor.core.dimension

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("dimension@px")
data class DimensionPx(
    override val value: Float
) : SDimension<Float> {
    override fun asCompose(): Float = value
}
