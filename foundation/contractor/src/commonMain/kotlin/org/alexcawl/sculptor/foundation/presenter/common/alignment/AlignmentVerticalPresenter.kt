package org.alexcawl.sculptor.foundation.presenter.common.alignment

import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import kotlin.reflect.KClass
import androidx.compose.ui.Alignment as ComposeAlignment
import org.alexcawl.sculptor.foundation.contract.common.Alignment as SculptorAlignment

public class AlignmentVerticalPresenter : Presenter<SculptorAlignment.Vertical, ComposeAlignment.Vertical>() {
    override val input: KClass<SculptorAlignment.Vertical> = SculptorAlignment.Vertical::class
    override val output: KClass<ComposeAlignment.Vertical> = ComposeAlignment.Vertical::class

    public override suspend fun PresenterScope.dslTransform(input: SculptorAlignment.Vertical): ComposeAlignment.Vertical {
        return when (input) {
            SculptorAlignment.Vertical.Bottom -> ComposeAlignment.Bottom
            SculptorAlignment.Vertical.Center -> ComposeAlignment.CenterVertically
            SculptorAlignment.Vertical.Top -> ComposeAlignment.Top
        }
    }
}
