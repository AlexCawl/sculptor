package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextStateContract
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextStateContract.TextType
import org.alexcawl.sculptor.foundation.layout.BasicTextLayout
import org.alexcawl.sculptor.foundation.presenter.LayoutPresenterTest

class BasicTextPresenterTest : LayoutPresenterTest<BasicTextLayoutContract, BasicTextStateContract>() {
    override val presenter: BasicTextPresenter = BasicTextPresenter()

    override val input: BasicTextLayoutContract = BasicTextLayoutContract(
        id = "basic_text".id,
        state = "state1".id,
        modifiers = emptyList(),
        states = listOf(
            BasicTextStateContract(
                id = "state1".id,
                modifiers = emptyList(),
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
