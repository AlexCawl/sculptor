package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Arrangement
import org.alexcawl.sculptor.foundation.contract.layout.RowLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.RowStateContract
import org.alexcawl.sculptor.foundation.layout.RowLayout
import org.alexcawl.sculptor.foundation.presenter.LayoutPresenterTest

class RowPresenterTest : LayoutPresenterTest<RowLayoutContract, RowStateContract>() {
    override val presenter: RowPresenter = RowPresenter()

    override val input: RowLayoutContract = RowLayoutContract(
        id = "row".id,
        state = "state1".id,
        modifiers = emptyList(),
        states = listOf(
            RowStateContract(
                id = "state1".id,
                modifiers = emptyList(),
                horizontalArrangement = Arrangement.Horizontal.Start,
                verticalAlignment = Alignment.Vertical.Top,
                content = emptyList(),
            )
        )
    )

    override val expected: Layout = RowLayout(
        id = "row@state1",
        modifier = Modifier,
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Start,
        verticalAlignment = androidx.compose.ui.Alignment.Top,
        content = emptyList(),
    )
}
