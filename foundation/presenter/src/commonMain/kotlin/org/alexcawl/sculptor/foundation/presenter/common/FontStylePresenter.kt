package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.ui.text.font.FontStyle as ComposeFontStyle
import org.alexcawl.sculptor.foundation.contract.common.FontStyle as SculptorFontStyle

public class FontStylePresenter : Presenter<SculptorFontStyle, ComposeFontStyle>() {
    override val input: KClass<SculptorFontStyle> = SculptorFontStyle::class
    override val output: KClass<ComposeFontStyle> = ComposeFontStyle::class

    override suspend fun PresenterScope.dslTransform(input: SculptorFontStyle): ComposeFontStyle {
        return when (input) {
            SculptorFontStyle.Normal -> ComposeFontStyle.Normal
            SculptorFontStyle.Italic -> ComposeFontStyle.Italic
        }
    }
}
