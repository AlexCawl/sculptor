package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Block
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Arrangement
import org.alexcawl.sculptor.foundation.contract.layout.ColumnState
import org.alexcawl.sculptor.foundation.layout.ColumnLayout
import org.alexcawl.sculptor.foundation.presenter.StatePresenterTest

class ColumnPresenterTest : StatePresenterTest<ColumnState>() {
    override val statePresenter: StatePresenter<ColumnState> = ColumnPresenter()

    override val input: Block<ColumnState> = Block(
        id = "row".id,
        state = "state1".id,
        modifiers = emptyList(),
        states = listOf(
            ColumnState(
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
