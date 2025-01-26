package org.alexcawl.skulptor.layout.common

import org.alexcawl.sculptor.common.BasePresenter
import org.alexcawl.sculptor.contract.common.Alignment
import kotlin.reflect.KClass
import androidx.compose.ui.Alignment as ComposeAlignment
import org.alexcawl.sculptor.contract.common.Alignment as SculptorAlignment

object AlignmentPresenter : BasePresenter<SculptorAlignment.Both, ComposeAlignment> {
    override val input: KClass<SculptorAlignment.Both> = SculptorAlignment.Both::class
    override val output: KClass<ComposeAlignment> = ComposeAlignment::class

    override fun transform(input: SculptorAlignment.Both): ComposeAlignment {
        return when (input) {
            Alignment.Both.BottomCenter -> ComposeAlignment.BottomCenter
            Alignment.Both.BottomEnd -> ComposeAlignment.BottomEnd
            Alignment.Both.BottomStart -> ComposeAlignment.BottomStart
            Alignment.Both.Center -> ComposeAlignment.Center
            Alignment.Both.CenterEnd -> ComposeAlignment.CenterEnd
            Alignment.Both.CenterStart -> ComposeAlignment.CenterStart
            Alignment.Both.TopCenter -> ComposeAlignment.TopCenter
            Alignment.Both.TopEnd -> ComposeAlignment.TopEnd
            Alignment.Both.TopStart -> ComposeAlignment.TopStart
        }
    }
}

object AlignmentHorizontalPresenter : BasePresenter<SculptorAlignment.Horizontal, ComposeAlignment.Horizontal> {
    override val input: KClass<SculptorAlignment.Horizontal> = SculptorAlignment.Horizontal::class
    override val output: KClass<ComposeAlignment.Horizontal> = ComposeAlignment.Horizontal::class

    override fun transform(input: SculptorAlignment.Horizontal): ComposeAlignment.Horizontal {
        return when(input) {
            Alignment.Horizontal.Center -> ComposeAlignment.CenterHorizontally
            Alignment.Horizontal.End -> ComposeAlignment.End
            Alignment.Horizontal.Start -> ComposeAlignment.Start
        }
    }
}

object AlignmentVerticalPresenter : BasePresenter<SculptorAlignment.Vertical, ComposeAlignment.Vertical> {
    override val input: KClass<SculptorAlignment.Vertical> = SculptorAlignment.Vertical::class
    override val output: KClass<ComposeAlignment.Vertical> = ComposeAlignment.Vertical::class

    override fun transform(input: SculptorAlignment.Vertical): ComposeAlignment.Vertical {
        return when (input) {
            Alignment.Vertical.Bottom -> ComposeAlignment.Bottom
            Alignment.Vertical.Center -> ComposeAlignment.CenterVertically
            Alignment.Vertical.Top -> ComposeAlignment.Top
        }
    }
}
