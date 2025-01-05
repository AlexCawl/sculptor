package org.alexcawl.skulptor.core.modifier.actions

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.skulptor.core.modifier.SkulptorModifier
import org.alexcawl.skulptor.core.dimension.DimensionDp

internal val heightSerializersModule = SerializersModule {
    polymorphic(SkulptorModifier::class) {
        subclass(HeightMaxSize::class)
        subclass(HeightExact::class)
    }
}

@Serializable
@SerialName("modifier@height_max_size")
data object HeightMaxSize : SkulptorModifier {
    @Composable
    override fun asCompose(): Modifier =
        Modifier.fillMaxHeight()
}

@Serializable
@SerialName("modifier@height_exact")
data class HeightExact(
    @SerialName("value")
    val value: DimensionDp
) : SkulptorModifier {
    @Composable
    override fun asCompose(): Modifier =
        Modifier.height(value.asCompose())
}
