package org.alexcawl.sculptor.common.presenter.test

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.common.presenter.StatePresenterTest
import org.alexcawl.sculptor.common.presenter.statePresenter
import org.alexcawl.sculptor.common.presenter.mock.Mock
import org.alexcawl.sculptor.common.presenter.mock.MockLayout
import org.alexcawl.sculptor.common.presenter.mock.MockStateContract

class MockStatePresenterTest : StatePresenterTest<MockStateContract>() {
    override val presenter: StatePresenter<MockStateContract>
        get() = statePresenter(
            stateContract = MockStateContract::class,
            transformer = { id, modifier, state ->
                MockLayout(
                    id = id,
                    modifier = modifier,
                    value = state.value.data,
                )
            }
        )

    override val input: MockStateContract
        get() = MockStateContract(
            id = "state1".id,
            value = Mock(
                data = "Hello World!",
            ),
        )

    override val expected: Layout
        get() = MockLayout(
            id = "test@state1",
            modifier = Modifier,
            value = "Hello World!",
        )
}
