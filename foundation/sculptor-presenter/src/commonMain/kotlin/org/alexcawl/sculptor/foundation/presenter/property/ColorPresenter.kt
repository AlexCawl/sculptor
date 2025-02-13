package org.alexcawl.sculptor.foundation.presenter.property

import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.ui.graphics.Color as ComposeColor
import org.alexcawl.sculptor.foundation.contract.property.Color as SculptorColor

class ColorPresenter : CommonPresenter<SculptorColor, ComposeColor>() {
    override val input: KClass<SculptorColor> = SculptorColor::class
    override val output: KClass<ComposeColor> = ComposeColor::class

    override fun PresenterScope.transform(input: SculptorColor): ComposeColor =
        ComposeColor(input.value)
}
