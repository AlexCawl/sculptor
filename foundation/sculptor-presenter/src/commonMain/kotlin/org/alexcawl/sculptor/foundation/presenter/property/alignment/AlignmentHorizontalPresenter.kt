package org.alexcawl.sculptor.foundation.presenter.property.alignment

import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.ui.Alignment as ComposeAlignment
import org.alexcawl.sculptor.foundation.contract.property.Alignment as SculptorAlignment

class AlignmentHorizontalPresenter : CommonPresenter<SculptorAlignment.Horizontal, ComposeAlignment.Horizontal>() {
    override val input: KClass<SculptorAlignment.Horizontal> = SculptorAlignment.Horizontal::class
    override val output: KClass<ComposeAlignment.Horizontal> = ComposeAlignment.Horizontal::class

    override fun PresenterScope.transform(input: SculptorAlignment.Horizontal): ComposeAlignment.Horizontal {
        return when(input) {
            SculptorAlignment.Horizontal.Center -> ComposeAlignment.CenterHorizontally
            SculptorAlignment.Horizontal.End -> ComposeAlignment.End
            SculptorAlignment.Horizontal.Start -> ComposeAlignment.Start
        }
    }
}
