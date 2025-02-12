package org.alexcawl.sculptor.foundation.presenter.property.arrangement

import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.foundation.layout.Arrangement as ComposeArrangement
import org.alexcawl.sculptor.foundation.contract.property.Arrangement as SculptorArrangement

class ArrangementPresenter : Presenter<SculptorArrangement, ComposeArrangement.HorizontalOrVertical>() {
    override val input: KClass<SculptorArrangement> = SculptorArrangement::class
    override val output: KClass<ComposeArrangement.HorizontalOrVertical> =
        ComposeArrangement.HorizontalOrVertical::class

    override fun PresenterScope.transform(input: SculptorArrangement): ComposeArrangement.HorizontalOrVertical {
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
