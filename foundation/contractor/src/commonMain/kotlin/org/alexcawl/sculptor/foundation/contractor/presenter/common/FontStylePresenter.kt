package org.alexcawl.sculptor.foundation.contractor.presenter.common

import org.alexcawl.sculptor.core.contractor.presenter.Presenter
import org.alexcawl.sculptor.core.contractor.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.ui.text.font.FontStyle as ComposeFontStyle
import org.alexcawl.sculptor.foundation.contract.common.FontStyle as SculptorFontStyle

internal class FontStylePresenter : Presenter<SculptorFontStyle, ComposeFontStyle>() {
    override val input: KClass<SculptorFontStyle> = SculptorFontStyle::class
    override val output: KClass<ComposeFontStyle> = ComposeFontStyle::class

    override suspend fun PresenterScope.dslTransform(input: SculptorFontStyle): ComposeFontStyle {
        return when (input) {
            SculptorFontStyle.Normal -> ComposeFontStyle.Normal
            SculptorFontStyle.Italic -> ComposeFontStyle.Italic
        }
    }
}
