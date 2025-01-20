package org.alexcawl.showroom.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.skulptor.builder.basicText
import org.alexcawl.skulptor.builder.column
import org.alexcawl.skulptor.builder.layout
import org.alexcawl.skulptor.builder.row
import org.alexcawl.skulptor.core.factory.BaseLayout
import org.alexcawl.skulptor.core.state.BaseState
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.foundation.layout.basictext.BasicTextLayout
import org.alexcawl.skulptor.foundation.layout.basictext.BasicTextState
import org.alexcawl.skulptor.foundation.layout.box.BoxLayout
import org.alexcawl.skulptor.foundation.layout.box.BoxState
import org.alexcawl.skulptor.foundation.layout.column.ColumnLayout
import org.alexcawl.skulptor.foundation.layout.column.ColumnState
import org.alexcawl.skulptor.foundation.layout.row.RowLayout
import org.alexcawl.skulptor.foundation.layout.row.RowState
import org.alexcawl.skulptor.modifier.BackgroundModifier
import org.alexcawl.skulptor.modifier.HeightModifier
import org.alexcawl.skulptor.modifier.WidthModifier

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
    val schema = layout {
        column(
            state = ColumnState("column0"),
        ) {
            basicText(
                state = BasicTextState("text0", "some text"),
            )
            basicText(
                state = BasicTextState("text1", "some text"),
            )
            row(
                state = RowState("row0"),
            ) {
                basicText(
                    state = BasicTextState("text5", "some text"),
                )
                basicText(
                    state = BasicTextState("text6", "some text"),
                )
            }
            basicText(
                state = BasicTextState("text2", "some text"),
            )
            basicText(
                state = BasicTextState("text3", "some text"),
            )
            basicText(
                state = BasicTextState("text4", "some text"),
            )
        }
    }

    Skulptor(
        rootLayoutId = "column0",
        state = Skulptor.State(
            schema = schema,
            dispatch = ::println
        ),
        modifier = Modifier.fillMaxSize()
    )
}
