package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.core.presenter.Presenter
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.mapEach
import kotlin.reflect.KClass
import androidx.compose.ui.text.style.TextDecoration as ComposeTextDecoration
import org.alexcawl.sculptor.foundation.contract.common.TextDecoration as SculptorTextDecoration

public class TextDecorationPresenter : Presenter<SculptorTextDecoration, ComposeTextDecoration>() {
    override val input: KClass<SculptorTextDecoration> = SculptorTextDecoration::class
    override val output: KClass<ComposeTextDecoration> = ComposeTextDecoration::class

    override suspend fun PresenterScope.dslTransform(input: SculptorTextDecoration): ComposeTextDecoration {
        return when (input) {
            is SculptorTextDecoration.None -> ComposeTextDecoration.None
            is SculptorTextDecoration.Underline -> ComposeTextDecoration.Underline
            is SculptorTextDecoration.LineThrough -> ComposeTextDecoration.LineThrough
            is SculptorTextDecoration.Combined -> ComposeTextDecoration.combine(
                decorations = mapEach(
                    input = input.decorations,
                )
            )
        }
    }
}
