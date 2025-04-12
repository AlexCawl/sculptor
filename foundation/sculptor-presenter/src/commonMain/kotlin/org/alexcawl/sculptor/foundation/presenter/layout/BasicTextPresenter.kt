package org.alexcawl.sculptor.foundation.presenter.layout

import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState
import org.alexcawl.sculptor.foundation.layout.BasicTextUiState
import kotlin.reflect.KClass

public class BasicTextPresenter : StatePresenter<BasicTextState>() {
    override val input: KClass<BasicTextState> = BasicTextState::class

    public override suspend fun PresenterScope.transform(input: BasicTextState): UiState {
        return with(input) {
            BasicTextUiState(
                text = text,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines,
            )
        }
    }
}
