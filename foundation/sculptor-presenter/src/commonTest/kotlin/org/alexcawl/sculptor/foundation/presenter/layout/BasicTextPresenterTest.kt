package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextState.TextType
import org.alexcawl.sculptor.foundation.layout.BasicTextLayout
import org.alexcawl.sculptor.foundation.presenter.StatePresenterTest

class BasicTextPresenterTest : StatePresenterTest<BasicTextState>() {
    override val statePresenter: StatePresenter<BasicTextState> = BasicTextPresenter()

    override val input: Section = Section(
        id = "basic_text".id,
        forcedState = "state1".id,
        modifiers = emptyList(),
        states = listOf(
            BasicTextState(
                id = "state1".id,
                softWrap = true,
                maxLines = 3,
                minLines = 1,
                textType = TextType.Static(
                    text = "Hello world!",
                ),
            )
        )
    )

    override val expected: Layout = BasicTextLayout(
        id = "basic_text@state1",
        modifier = Modifier,
        text = "Hello world!",
        softWrap = true,
        maxLines = 3,
        minLines = 1,
    )
}
