package org.alexcawl.sculptor.common.presenter.test

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Block
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.BlockPresenter
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.PresenterTest
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.common.presenter.mock.Mock
import org.alexcawl.sculptor.common.presenter.mock.MockLayout
import org.alexcawl.sculptor.common.presenter.mock.MockStateContract
import org.alexcawl.sculptor.common.presenter.statePresenter

class BlockPresenterTest : PresenterTest<Block<*>, Layout>() {
    private val mockPresenter: StatePresenter<MockStateContract>
        get() = statePresenter(
            stateContract = MockStateContract::class,
            transformer = { id, modifier, state ->
                MockLayout(
                    id = id + state.id,
                    modifier = modifierMap(input = modifier + state.modifiers),
                    value = state.value.data,
                )
            }
        )

    override val presenterScope: PresenterScope
        get() = super.presenterScope.copy(
            presenterProvider = { _, _ -> mockPresenter },
            blockProvider = { _ -> input }
        )

    override val presenter: Presenter<Block<*>, Layout> = BlockPresenter

    override val input: Block<MockStateContract>
        get() = Block(
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

    override val expected: Layout
        get() = MockLayout(
            id = "test@state1",
            modifier = Modifier,
            value = "Hello World!",
        )
}