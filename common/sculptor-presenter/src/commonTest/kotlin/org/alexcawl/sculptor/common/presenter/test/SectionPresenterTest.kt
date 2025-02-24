package org.alexcawl.sculptor.common.presenter.test

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.PresenterTest
import org.alexcawl.sculptor.common.presenter.SectionCompositePresenter
import org.alexcawl.sculptor.common.presenter.StatePresenter
import org.alexcawl.sculptor.common.presenter.mock.Mock
import org.alexcawl.sculptor.common.presenter.mock.MockLayout
import org.alexcawl.sculptor.common.presenter.mock.MockStateContract
import org.alexcawl.sculptor.common.presenter.statePresenter

class SectionPresenterTest : PresenterTest<Section.Composite, Layout>() {
    private val mockPresenter: StatePresenter<MockStateContract>
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

    @OptIn(InternalSculptorApi::class)
    override val presenterScope: PresenterScope
        get() = PresenterScope(
            presenters = listOf(mockPresenter),
            sections = listOf(input),
        )

    override val presenter: Presenter<Section.Composite, Layout> = SectionCompositePresenter

    override val input: Section.Composite
        get() = Section.Composite(
            id = "test".id,
            forcedState = "state1".id,
            modifiers = listOf(),
            states = listOf(
                MockStateContract(
                    id = "state1".id,
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
