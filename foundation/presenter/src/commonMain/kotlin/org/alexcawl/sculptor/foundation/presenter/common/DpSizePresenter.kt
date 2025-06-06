package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.map
import kotlin.reflect.KClass
import androidx.compose.ui.unit.DpSize as ComposeDpSize
import org.alexcawl.sculptor.foundation.contract.common.DpSize as SculptorDpSize

public class DpSizePresenter : Presenter<SculptorDpSize, ComposeDpSize>() {
    override val input: KClass<SculptorDpSize> = SculptorDpSize::class
    override val output: KClass<ComposeDpSize> = ComposeDpSize::class

    public override suspend fun PresenterScope.dslTransform(input: SculptorDpSize): ComposeDpSize {
        return when (input) {
            SculptorDpSize.Zero -> ComposeDpSize.Zero
            SculptorDpSize.Unspecified -> ComposeDpSize.Unspecified
            is SculptorDpSize.Content -> ComposeDpSize(
                width = map(input.width),
                height = map(input.height)
            )
        }
    }
}
