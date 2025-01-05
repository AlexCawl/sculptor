package org.alexcawl.skulptor.core.modifier.actions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.skulptor.core.modifier.SkulptorModifier
import org.alexcawl.skulptor.core.dimension.DimensionDp

internal val widthSerializersModule = SerializersModule {
    polymorphic(SkulptorModifier::class) {
        subclass(WidthMaxSize::class)
        subclass(WidthExact::class)
    }
}

@Serializable
@SerialName("modifier@width_max_size")
data object WidthMaxSize : SkulptorModifier {
    @Composable
    override fun asCompose(): Modifier =
        Modifier.fillMaxWidth()
}

@Serializable
@SerialName("modifier@width_exact")
data class WidthExact(
    @SerialName("value")
    val value: DimensionDp
) : SkulptorModifier {
    @Composable
    override fun asCompose(): Modifier =
        Modifier.width(value.asCompose())
}
