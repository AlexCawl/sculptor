package org.alexcawl.showroom.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.foundation.BasicTextLayout
import org.alexcawl.skulptor.core.foundation.BoxLayout
import org.alexcawl.skulptor.core.foundation.ColumnLayout
import org.alexcawl.skulptor.core.foundation.RowLayout
import org.alexcawl.skulptor.core.modifier.BackgroundModifier
import org.alexcawl.skulptor.core.modifier.HeightModifier
import org.alexcawl.skulptor.core.modifier.WidthModifier
import org.alexcawl.skulptor.core.provider.AlignmentProvider
import org.alexcawl.skulptor.core.provider.ColorProvider
import org.alexcawl.skulptor.core.provider.DpProvider
import org.alexcawl.skulptor.core.provider.ShapeProvider

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
            subclass(WidthModifier.FillMaxWidth::class)
            subclass(HeightModifier.FillMaxHeight::class)
            subclass(WidthModifier.Width::class)
            subclass(HeightModifier.Height::class)
        }
    }
}

@Composable
fun App() {
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
            contentAlignment = AlignmentProvider.HorizontalAndVertical(Alignment.Center),
            content = BasicTextLayout(
                id = "text0",
                modifiers = listOf(
                    BackgroundModifier.Background(
                        color = ColorProvider(Color.Blue),
                        shape = ShapeProvider.Rectangle
                    ),
                    HeightModifier.Height(DpProvider.Number(96.0f))
                ),
                state = BasicTextLayout.State.Base(
                    text = "some text"
                )
            )
        )
    )
    println(format.encodeToString(layout))
    val root = Skulptor.root()
    root.place(layout)
}
