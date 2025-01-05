package org.alexcawl.skulptor.core.dimension

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("dimension@dp")
data class DimensionDp(
    override val value: Int
) : SDimension<Dp> {
    override fun asCompose(): Dp = value.dp
}
