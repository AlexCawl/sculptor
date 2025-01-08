package org.alexcawl.skulptor.core

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.skulptor.core.foundation.BasicTextLayout
import org.alexcawl.skulptor.core.foundation.BoxLayout
import org.alexcawl.skulptor.core.foundation.ColumnLayout
import org.alexcawl.skulptor.core.foundation.RowLayout
import org.alexcawl.skulptor.core.modifier.BackgroundModifier
import org.junit.Test

class Test {
    val format = Json {
        serializersModule = SerializersModule {
            polymorphic(SkulptorLayout::class) {
                subclass(BasicTextLayout::class)
                subclass(BoxLayout::class)
                subclass(ColumnLayout::class)
                subclass(RowLayout::class)
            }
            polymorphic(SkulptorModifier::class) {
                subclass(BackgroundModifier.Background::class)
            }
        }
    }

    @Test
    fun test() {

    }
}
