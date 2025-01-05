package org.alexcawl.showroom.app

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.skulptor.core.dimension.SkulptorDimension
import org.alexcawl.skulptor.core.SkulptorKey
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.modifier.SkulptorModifier
import org.alexcawl.skulptor.core.state.SkulptorState
import org.alexcawl.skulptor.core.dimension.DimensionDp
import org.alexcawl.skulptor.core.dimension.DimensionPx
import org.alexcawl.skulptor.core.dimension.DimensionSp
import org.alexcawl.skulptor.core.modifier.actions.HeightExact
import org.alexcawl.skulptor.core.modifier.actions.HeightMaxSize
import org.alexcawl.skulptor.core.modifier.actions.WidthExact
import org.alexcawl.skulptor.core.modifier.actions.WidthMaxSize
import org.alexcawl.skulptor.core.state.foundation.BoxState

fun main() {
    val format = Json {
        this.serializersModule = module
    }
    val layout = SkulptorLayout(
        id = "box_0",
        key = SkulptorKey("foundation", "box"),
        modifier = listOf(
            WidthMaxSize,
            HeightExact(DimensionDp(120)),
        ),
        state = BoxState()
    )
    println(layout)
    val text = format.encodeToString(layout)
    println(text)
    val layout1: SkulptorLayout = format.decodeFromString(text)
    println(layout1)
}

val module = SerializersModule {
    polymorphic(SkulptorModifier::class) {
        subclass(WidthMaxSize::class)
        subclass(WidthExact::class)
    }
    polymorphic(SkulptorModifier::class) {
        subclass(HeightMaxSize::class)
        subclass(HeightExact::class)
    }
    polymorphic(SkulptorDimension::class) {
        subclass(DimensionDp::class)
        subclass(DimensionSp::class)
        subclass(DimensionPx::class)
    }
    polymorphic(SkulptorState::class) {
        subclass(BoxState::class)
    }
}
