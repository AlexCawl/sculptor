package org.alexcawl.sculptor.foundation.presenter.common.alignment

import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.ui.Alignment as ComposeAlignment
import org.alexcawl.sculptor.foundation.contract.common.Alignment as SculptorAlignment

public class AlignmentHorizontalPresenter : Presenter<SculptorAlignment.Horizontal, ComposeAlignment.Horizontal>() {
    override val input: KClass<SculptorAlignment.Horizontal> = SculptorAlignment.Horizontal::class
    override val output: KClass<ComposeAlignment.Horizontal> = ComposeAlignment.Horizontal::class

    override suspend fun PresenterScope.dslTransform(input: SculptorAlignment.Horizontal): ComposeAlignment.Horizontal {
        return when(input) {
            SculptorAlignment.Horizontal.Center -> ComposeAlignment.CenterHorizontally
            SculptorAlignment.Horizontal.End -> ComposeAlignment.End
            SculptorAlignment.Horizontal.Start -> ComposeAlignment.Start
        }
    }
}
