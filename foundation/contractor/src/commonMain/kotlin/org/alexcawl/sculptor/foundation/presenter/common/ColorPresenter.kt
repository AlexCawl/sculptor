package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.common.Color
import kotlin.reflect.KClass
import androidx.compose.ui.graphics.Color as ComposeColor
import org.alexcawl.sculptor.foundation.contract.common.Color as SculptorColor

public class ColorPresenter : Presenter<SculptorColor, ComposeColor>() {
    override val input: KClass<SculptorColor> = SculptorColor::class
    override val output: KClass<ComposeColor> = ComposeColor::class

    public override suspend fun PresenterScope.dslTransform(input: SculptorColor): ComposeColor {
        return with(input) {
            when (input) {
                is Color.RGB -> ComposeColor(r, g, b)
                is Color.RGBA -> ComposeColor(r, g, b, a)
                Color.Unspecified -> ComposeColor.Unspecified
            }
        }
    }
}
