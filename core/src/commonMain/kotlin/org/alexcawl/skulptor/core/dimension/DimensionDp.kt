package org.alexcawl.skulptor.core.dimension

import androidx.compose.ui.unit.dp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private typealias ComposeDp = androidx.compose.ui.unit.Dp

@Serializable
@SerialName("dimension@dp")
data class DimensionDp(
    override val value: Int
) : SkulptorDimension<ComposeDp> {
    override fun asCompose(): ComposeDp = value.dp
}
