package org.alexcawl.sculptor.foundation.presenter.property.alignment

import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.ui.Alignment as ComposeAlignment
import org.alexcawl.sculptor.foundation.contract.property.Alignment as SculptorAlignment

class AlignmentPresenter : CommonPresenter<SculptorAlignment, ComposeAlignment>() {
    override val input: KClass<SculptorAlignment> = SculptorAlignment::class
    override val output: KClass<ComposeAlignment> = ComposeAlignment::class

    override fun PresenterScope.transform(input: SculptorAlignment): ComposeAlignment {
        return when (input) {
            SculptorAlignment.BottomCenter -> ComposeAlignment.BottomCenter
            SculptorAlignment.BottomEnd -> ComposeAlignment.BottomEnd
            SculptorAlignment.BottomStart -> ComposeAlignment.BottomStart
            SculptorAlignment.Center -> ComposeAlignment.Center
            SculptorAlignment.CenterEnd -> ComposeAlignment.CenterEnd
            SculptorAlignment.CenterStart -> ComposeAlignment.CenterStart
            SculptorAlignment.TopCenter -> ComposeAlignment.TopCenter
            SculptorAlignment.TopEnd -> ComposeAlignment.TopEnd
            SculptorAlignment.TopStart -> ComposeAlignment.TopStart
        }
    }
}
