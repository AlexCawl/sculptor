package org.alexcawl.sculptor.foundation.contractor.presenter.common

import org.alexcawl.sculptor.core.contractor.presenter.Presenter
import org.alexcawl.sculptor.core.contractor.presenter.PresenterScope
import org.alexcawl.sculptor.core.contractor.presenter.map
import kotlin.reflect.KClass
import androidx.compose.ui.unit.DpSize as ComposeDpSize
import org.alexcawl.sculptor.foundation.contract.common.DpSize as SculptorDpSize

internal class DpSizePresenter : Presenter<SculptorDpSize, ComposeDpSize>() {
    override val input: KClass<SculptorDpSize> = SculptorDpSize::class
    override val output: KClass<ComposeDpSize> = ComposeDpSize::class

    override suspend fun PresenterScope.dslTransform(input: SculptorDpSize): ComposeDpSize {
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
