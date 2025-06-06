package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.map
import kotlin.reflect.KClass
import androidx.compose.ui.text.TextStyle as ComposeTextStyle
import org.alexcawl.sculptor.foundation.contract.common.TextStyle as SculptorTextStyle

public class TextStylePresenter : Presenter<SculptorTextStyle, ComposeTextStyle>() {
    override val input: KClass<SculptorTextStyle> = SculptorTextStyle::class
    override val output: KClass<ComposeTextStyle> = ComposeTextStyle::class

    override suspend fun PresenterScope.dslTransform(input: SculptorTextStyle): ComposeTextStyle {
        return ComposeTextStyle(
            color = map(input.color),
            fontSize = map(input.fontSize),
            fontStyle = map(input.fontStyle),
            background = map(input.background),
            textDecoration = map(input.textDecoration),
        )
    }
}
