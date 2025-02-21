package org.alexcawl.sculptor.common.presenter.test

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.LayoutPresenterTest
import org.alexcawl.sculptor.common.presenter.layoutPresenter
import org.alexcawl.sculptor.common.presenter.mock.Mock
import org.alexcawl.sculptor.common.presenter.mock.MockLayout
import org.alexcawl.sculptor.common.presenter.mock.MockLayoutContract
import org.alexcawl.sculptor.common.presenter.mock.MockStateContract

class MockLayoutPresenterTest : LayoutPresenterTest<MockLayoutContract, MockStateContract>() {
    override val presenter: LayoutPresenter<MockLayoutContract, MockStateContract> =
        layoutPresenter(
            layoutContract = MockLayoutContract::class,
            transformer = { id, modifier, state ->
                MockLayout(
                    id = id,
                    modifier = modifier,
                    value = state.value.data,
                )
            }
        )

    override val input: MockLayoutContract = MockLayoutContract(
        id = "test".id,
        state = "state1".id,
        modifiers = listOf(),
        states = listOf(
            MockStateContract(
                id = "state1".id,
                modifiers = listOf(),
                value = Mock(
                    data = "Hello World!",
                ),
            ),
        ),
    )

    override val expected: Layout = MockLayout(
        id = "test@state1",
        modifier = Modifier,
        value = "Hello World!",
    )
}
