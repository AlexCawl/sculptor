package org.alexcawl.sculptor.foundation.contractor.presenter.layout

import org.alexcawl.sculptor.core.contractor.layout.UiState
import org.alexcawl.sculptor.core.contractor.presenter.PresenterScope
import org.alexcawl.sculptor.core.contractor.presenter.StatePresenter
import org.alexcawl.sculptor.core.contractor.presenter.map
import org.alexcawl.sculptor.foundation.contract.state.BasicTextState
import org.alexcawl.sculptor.foundation.contractor.state.BasicTextUiState
import kotlin.reflect.KClass

internal class BasicTextPresenter : StatePresenter<BasicTextState>() {
    override val input: KClass<BasicTextState> = BasicTextState::class

    override suspend fun PresenterScope.dslTransform(input: BasicTextState): UiState {
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
