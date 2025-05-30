package org.alexcawl.sculptor.foundation.contractor.presenter.common.arrangement

import org.alexcawl.sculptor.core.contractor.presenter.Presenter
import org.alexcawl.sculptor.core.contractor.presenter.PresenterScope
import org.alexcawl.sculptor.core.contractor.presenter.map
import kotlin.reflect.KClass
import androidx.compose.foundation.layout.Arrangement as ComposeArrangement
import org.alexcawl.sculptor.foundation.contract.common.Arrangement as SculptorArrangement

internal class ArrangementPresenter : Presenter<SculptorArrangement, ComposeArrangement.HorizontalOrVertical>() {
    override val input: KClass<SculptorArrangement> = SculptorArrangement::class
    override val output: KClass<ComposeArrangement.HorizontalOrVertical> =
        ComposeArrangement.HorizontalOrVertical::class

    override suspend fun PresenterScope.dslTransform(input: SculptorArrangement): ComposeArrangement.HorizontalOrVertical {
        return when (input) {
            SculptorArrangement.Center -> ComposeArrangement.Center
            SculptorArrangement.SpaceAround -> ComposeArrangement.SpaceAround
            SculptorArrangement.SpaceBetween -> ComposeArrangement.SpaceBetween
            SculptorArrangement.SpaceEvenly -> ComposeArrangement.SpaceEvenly

            is SculptorArrangement.SpacedBy -> ComposeArrangement.spacedBy(
                space = map(input.space)
            )
        }
    }
}
