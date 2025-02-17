package org.alexcawl.sculptor.foundation.presenter.layout

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.layout.BoxLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BoxStateContract
import org.alexcawl.sculptor.foundation.layout.BoxLayout
import org.alexcawl.sculptor.foundation.presenter.LayoutPresenterTest

class BoxPresenterTest : LayoutPresenterTest<BoxLayoutContract, BoxStateContract>() {
    override val presenter: BoxPresenter = BoxPresenter()

    override val input: BoxLayoutContract = BoxLayoutContract(
        id = "box".id,
        state = "state1".id,
        modifiers = emptyList(),
        states = listOf(
            BoxStateContract(
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
