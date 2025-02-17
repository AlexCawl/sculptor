package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Arrangement
import org.alexcawl.sculptor.foundation.contract.layout.ColumnLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.ColumnStateContract
import org.alexcawl.sculptor.foundation.layout.ColumnLayout
import org.alexcawl.sculptor.foundation.presenter.LayoutPresenterTest

class ColumnPresenterTest : LayoutPresenterTest<ColumnLayoutContract, ColumnStateContract>() {
    override val presenter: ColumnPresenter = ColumnPresenter()

    override val input: ColumnLayoutContract = ColumnLayoutContract(
        id = "row".id,
        state = "state1".id,
        modifiers = emptyList(),
        states = listOf(
            ColumnStateContract(
                id = "state1".id,
                modifiers = emptyList(),
                verticalArrangement = Arrangement.Vertical.Top,
                horizontalAlignment = Alignment.Horizontal.Start,
                content = emptyList(),
            )
        )
    )

    override val expected: Layout = ColumnLayout(
        id = "row@state1",
        modifier = Modifier,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Top,
        horizontalAlignment = androidx.compose.ui.Alignment.Start,
        content = emptyList(),
    )
}
