package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.ui.unit.TextUnit as ComposeTextUnit
import androidx.compose.ui.unit.sp
import org.alexcawl.sculptor.foundation.contract.common.TextUnit as SculptorTextUnit


public class TextUnitPresenter : Presenter<SculptorTextUnit, ComposeTextUnit>() {
    override val input: KClass<SculptorTextUnit> = SculptorTextUnit::class
    override val output: KClass<ComposeTextUnit> = ComposeTextUnit::class

    override suspend fun PresenterScope.dslTransform(input: SculptorTextUnit): ComposeTextUnit {
        return when (input) {
            SculptorTextUnit.unspecified -> ComposeTextUnit.Unspecified
            else -> input.value.toInt().sp
        }
    }
}