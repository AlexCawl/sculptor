package org.alexcawl.skulptor.core

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.skulptor.core.foundation.BasicTextLayout
import org.alexcawl.skulptor.core.foundation.BoxLayout
import org.alexcawl.skulptor.core.foundation.ColumnLayout
import org.alexcawl.skulptor.core.foundation.RowLayout
import org.alexcawl.skulptor.core.modifier.BackgroundModifier
import org.alexcawl.skulptor.core.modifier.HeightModifier
import org.alexcawl.skulptor.core.modifier.WidthModifier
import org.alexcawl.skulptor.core.provider.ColorProvider
import org.alexcawl.skulptor.core.provider.ShapeProvider
import org.junit.Test

class Test {
    private val format = Json {
        serializersModule = SerializersModule {
            polymorphic(SkulptorLayout::class) {
                subclass(BasicTextLayout::class)
                subclass(BoxLayout::class)
                subclass(ColumnLayout::class)
                subclass(RowLayout::class)
            }
            polymorphic(SkulptorModifier::class) {
                subclass(BackgroundModifier.Background::class)
                subclass(WidthModifier.FillMaxWidth::class)
                subclass(HeightModifier.FillMaxHeight::class)
            }

        }
    }

    @Test
    fun test() {
        val layout: SkulptorLayout = BoxLayout(
            id = "box0",
            modifiers = listOf(
                BackgroundModifier.Background(
                    color = ColorProvider(Color.Red),
                    shape = ShapeProvider.Circle
                ),
                WidthModifier.FillMaxWidth(fraction = 1.0f)
            ),
            state = BoxLayout.State(
                content = BasicTextLayout(
                    id = "text0",
                    modifiers = listOf(
                        BackgroundModifier.Background(
                            color = ColorProvider(Color.Blue),
                            shape = ShapeProvider.Rectangle
                        ),
                        HeightModifier.FillMaxHeight(fraction = 1.0f)
                    ),
                    state = BasicTextLayout.State.Base(
                        text = "some text"
                    )
                )
            )
        )
        println(format.encodeToString(layout))
    }
}
