package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.SectionCompositePresenter
import org.alexcawl.sculptor.common.presenter.StatePresenter

abstract class StatePresenterTest<SC : StateContract> : PresenterTest<Section.Composite, Layout>() {
    final override val presenters: List<Presenter<*, *>>
        get() = super.presenters + presenter + statePresenter

    final override val presenter: SectionCompositePresenter
        get() = SectionCompositePresenter

    @OptIn(InternalSculptorApi::class)
    final override val presenterScope: PresenterScope
        get() = PresenterScope(
            presenters = presenters,
            sections = listOf(input),
        )

    abstract val statePresenter: StatePresenter<SC>
    abstract override val input: Section.Composite
    abstract override val expected: Layout
}
