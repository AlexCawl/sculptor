package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.contract.Block
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.BlockPresenter
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.StatePresenter

abstract class StatePresenterTest<SC : StateContract> : PresenterTest<Block<*>, Layout>() {
    final override val presenters: List<Presenter<*, *>>
        get() = super.presenters + presenter + statePresenter

    final override val presenter: BlockPresenter
        get() = BlockPresenter

    final override val presenterScope: PresenterScope
        get() = super.presenterScope.copy(
            blockProvider = { _ -> input }
        )

    abstract val statePresenter: StatePresenter<SC>
    abstract override val input: Block<SC>
    abstract override val expected: Layout
}
