package org.alexcawl.sculptor.foundation.presenter.layout

import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.StatePresenter
import org.alexcawl.sculptor.core.presenter.map
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState
import org.alexcawl.sculptor.foundation.layout.BasicTextUiState
import kotlin.reflect.KClass

public class BasicTextPresenter : StatePresenter<BasicTextState>() {
    override val input: KClass<BasicTextState> = BasicTextState::class

    public override suspend fun PresenterScope.dslTransform(input: BasicTextState): UiState {
        return with(input) {
            BasicTextUiState(
                text = text,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines,
                textStyle = map(textStyle),
            )
        }
    }
}
