package org.alexcawl.skulptor.core.dimension

import androidx.compose.ui.unit.DpSize
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SAttribute

@Serializable
data class SDimensionDpSize(
    val width: SDimensionDp,
    val height: SDimensionDp,
) : SAttribute<DpSize> {
    override fun asCompose(): DpSize =
        DpSize(
            width = width.asCompose(),
            height = height.asCompose()
        )
}
