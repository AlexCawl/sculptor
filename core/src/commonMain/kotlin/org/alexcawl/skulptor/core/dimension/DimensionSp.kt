package org.alexcawl.skulptor.core.dimension

import androidx.compose.ui.unit.sp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private typealias ComposeSp = androidx.compose.ui.unit.TextUnit

@Serializable
@SerialName("dimension@sp")
data class DimensionSp(override val value: Int) : SkulptorDimension<ComposeSp> {
    override fun asCompose(): ComposeSp = value.sp
}
