package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Block
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.layout.BoxState
import org.alexcawl.sculptor.foundation.layout.BoxLayout
import org.alexcawl.sculptor.foundation.presenter.StatePresenterTest

class BoxPresenterTest : StatePresenterTest<BoxState>() {
    override val statePresenter: StatePresenter<BoxState> = BoxPresenter()

    override val input: Block<BoxState> = Block(
        id = "box".id,
        state = "state1".id,
        modifiers = emptyList(),
        states = listOf(
            BoxState(
                id = "state1".id,
                modifiers = emptyList(),
                contentAlignment = Alignment.TopStart,
                propagateMinConstraints = false,
                content = emptyList(),
            )
        )
    )

    override val expected: Layout = BoxLayout(
        id = "box@state1",
        modifier = Modifier,
        contentAlignment = androidx.compose.ui.Alignment.TopStart,
        propagateMinConstraints = false,
        content = emptyList(),
    )
}
