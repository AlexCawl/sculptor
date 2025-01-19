package org.alexcawl.showroom.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.skulptor.core.factory.BaseLayout
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.core.SkulptorSchema
import org.alexcawl.skulptor.foundation.layout.basictext.BasicTextLayout
import org.alexcawl.skulptor.foundation.layout.basictext.BasicTextState
import org.alexcawl.skulptor.foundation.layout.box.BoxLayout
import org.alexcawl.skulptor.foundation.layout.box.BoxState
import org.alexcawl.skulptor.foundation.layout.column.ColumnLayout
import org.alexcawl.skulptor.foundation.layout.row.RowLayout
import org.alexcawl.skulptor.modifier.BackgroundModifier
import org.alexcawl.skulptor.modifier.HeightModifier
import org.alexcawl.skulptor.modifier.WidthModifier
import org.alexcawl.skulptor.provider.AlignmentProvider
import org.alexcawl.skulptor.provider.ColorProvider
import org.alexcawl.skulptor.provider.DpProvider
import org.alexcawl.skulptor.provider.ShapeProvider

val format = Json {
    serializersModule = SerializersModule {
        polymorphic(BaseLayout::class) {
            subclass(BasicTextLayout::class)
            subclass(BoxLayout::class)
            subclass(ColumnLayout::class)
            subclass(RowLayout::class)
        }
        polymorphic(BaseModifier::class) {
            subclass(BackgroundModifier.Background::class)
            subclass(WidthModifier.FillMaxWidth::class)
            subclass(HeightModifier.FillMaxHeight::class)
            subclass(WidthModifier.Width::class)
            subclass(HeightModifier.Height::class)
        }
        polymorphic(BaseState::class) {
            subclass(BasicTextState::class)
            subclass(BoxState::class)
        }
    }
}

@Composable
fun App() {
    val schema = SkulptorSchema(
        layouts = listOf(
            BoxLayout(
                id = "box0",
                modifiers = listOf(
                    BackgroundModifier.Background(
                        color = ColorProvider(Color.Red),
                        shape = ShapeProvider.Circle
                    ),
                    WidthModifier.FillMaxWidth(fraction = 1.0f)
                ),
            ),
            BasicTextLayout(
                id = "text0",
                modifiers = listOf(
                    BackgroundModifier.Background(
                        color = ColorProvider(Color.Blue),
                        shape = ShapeProvider.Rectangle
                    ),
                    HeightModifier.Height(DpProvider.Number(96.0f))
                ),
            )
        ),
        states = listOf(
            BoxState(
                id = "box0",
                contentAlignment = AlignmentProvider.HorizontalAndVertical(Alignment.Center),
                content = listOf("text0")
            ),
            BasicTextState(
                id = "text0",
                text = "some text"
            ),
        )
    )

    Skulptor(
        rootLayoutId = "box0",
        state = Skulptor.State(
            schema = schema,
            dispatch = ::println
        ),
        modifier = Modifier.fillMaxSize()
    )
}
