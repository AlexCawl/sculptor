package org.alexcawl.sculptor.foundation.presenter.common.arrangement

import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.map
import kotlin.reflect.KClass
import androidx.compose.foundation.layout.Arrangement as ComposeArrangement
import androidx.compose.ui.Alignment as ComposeAlignment
import org.alexcawl.sculptor.foundation.contract.common.Alignment as SculptorAlignment
import org.alexcawl.sculptor.foundation.contract.common.Arrangement as SculptorArrangement

public class ArrangementHorizontalPresenter : Presenter<SculptorArrangement.Horizontal, ComposeArrangement.Horizontal>() {
    override val input: KClass<SculptorArrangement.Horizontal> = SculptorArrangement.Horizontal::class
    override val output: KClass<ComposeArrangement.Horizontal> = ComposeArrangement.Horizontal::class

    public override suspend fun PresenterScope.transform(input: SculptorArrangement.Horizontal): ComposeArrangement.Horizontal {
        return when (input) {
            SculptorArrangement.Horizontal.Start -> ComposeArrangement.Start
            SculptorArrangement.Horizontal.Center -> ComposeArrangement.Center
            SculptorArrangement.Horizontal.End -> ComposeArrangement.End

            is SculptorArrangement.Horizontal.Aligned -> ComposeArrangement.aligned(
                alignment =  map<SculptorAlignment.Horizontal, ComposeAlignment.Horizontal>(
                    input = input.alignment
                )
            )

            is SculptorArrangement.Horizontal.SpacedBy -> ComposeArrangement.spacedBy(
                space = map(input.space)
            )
        }
    }
}
