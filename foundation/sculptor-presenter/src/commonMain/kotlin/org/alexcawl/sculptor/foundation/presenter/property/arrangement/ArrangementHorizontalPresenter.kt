package org.alexcawl.sculptor.foundation.presenter.property.arrangement

import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.foundation.layout.Arrangement as ComposeArrangement
import androidx.compose.ui.Alignment as ComposeAlignment
import org.alexcawl.sculptor.foundation.contract.property.Alignment as SculptorAlignment
import org.alexcawl.sculptor.foundation.contract.property.Arrangement as SculptorArrangement

class ArrangementHorizontalPresenter : Presenter<SculptorArrangement.Horizontal, ComposeArrangement.Horizontal>() {
    override val input: KClass<SculptorArrangement.Horizontal> = SculptorArrangement.Horizontal::class
    override val output: KClass<ComposeArrangement.Horizontal> = ComposeArrangement.Horizontal::class

    override fun PresenterScope.transform(input: SculptorArrangement.Horizontal): ComposeArrangement.Horizontal {
        return when (input) {
            SculptorArrangement.Horizontal.Start -> ComposeArrangement.Start
            SculptorArrangement.Horizontal.Center -> ComposeArrangement.Center
            SculptorArrangement.Horizontal.End -> ComposeArrangement.End

            is SculptorArrangement.Horizontal.Aligned -> ComposeArrangement.aligned(
                alignment = transform<SculptorAlignment.Horizontal, ComposeAlignment.Horizontal>(
                    input = input.alignment
                )
            )

            is SculptorArrangement.Horizontal.SpacedBy -> ComposeArrangement.spacedBy(
                space = transform(input.space)
            )
        }
    }
}
