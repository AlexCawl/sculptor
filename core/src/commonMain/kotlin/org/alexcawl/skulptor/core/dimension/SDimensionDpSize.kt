package org.alexcawl.skulptor.core.dimension

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SAttribute

/**
 * A two-dimensional Size using [Dp] for units.
 */
@Serializable
data class SDimensionDpSize(
    @SerialName("width")
    val width: SDimensionDp,
    @SerialName("height")
    val height: SDimensionDp,
) : SAttribute<DpSize> {
    override fun asCompose(): DpSize =
        DpSize(
            width = width.asCompose(),
            height = height.asCompose()
        )
}
