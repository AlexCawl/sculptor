package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.layout.Layout

abstract class StatePresenterTest<SC : StateContract> : PresenterTest<SC, Layout>() {
    abstract override val presenter: StatePresenter<SC>
    abstract override val input: SC
    abstract override val expected: Layout
}
