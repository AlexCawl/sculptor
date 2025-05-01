package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.ui.unit.Dp as ComposeDp
import org.alexcawl.sculptor.foundation.contract.common.Dp as SculptorDp

public class DpPresenter : Presenter<SculptorDp, ComposeDp>() {
    override val input: KClass<SculptorDp> = SculptorDp::class
    override val output: KClass<ComposeDp> = ComposeDp::class

    override suspend fun PresenterScope.transform(input: SculptorDp): ComposeDp {
        return when (input) {
            SculptorDp.Hairline -> ComposeDp.Hairline
            SculptorDp.Infinity -> ComposeDp.Infinity
            SculptorDp.Unspecified -> ComposeDp.Unspecified
            is SculptorDp.Number -> ComposeDp(value = input.value)
        }
    }
}
