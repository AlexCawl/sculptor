package org.alexcawl.skulptor.core.dimension

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("dimension@em")
data class DimensionEm(
    override val value: Int
) : SDimension<TextUnit> {
    override fun asCompose(): TextUnit = value.em
}
