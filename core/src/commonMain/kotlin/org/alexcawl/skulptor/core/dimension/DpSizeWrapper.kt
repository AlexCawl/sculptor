package org.alexcawl.skulptor.core.dimension

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAttribute

/**
 * A two-dimensional Size using [Dp] for units.
 */
@Serializable
data class DpSizeWrapper(
    @SerialName("width")
    val width: DpWrapper,
    @SerialName("height")
    val height: DpWrapper,
) : SkulptorAttribute<DpSize> {
    override fun asCompose(): DpSize =
        DpSize(
            width = width.asCompose(),
            height = height.asCompose()
        )
}
