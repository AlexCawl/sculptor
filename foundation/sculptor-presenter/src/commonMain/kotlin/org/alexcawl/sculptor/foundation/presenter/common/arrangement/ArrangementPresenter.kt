package org.alexcawl.sculptor.foundation.presenter.common.arrangement

import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.foundation.layout.Arrangement as ComposeArrangement
import org.alexcawl.sculptor.foundation.contract.common.Arrangement as SculptorArrangement

public class ArrangementPresenter : CommonPresenter<SculptorArrangement, ComposeArrangement.HorizontalOrVertical>() {
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
