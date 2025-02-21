package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.layout.Layout

abstract class LayoutPresenterTest<LC : LayoutContract, SC : StateContract> : PresenterTest<LC, Layout>() {
    abstract override val presenter: LayoutPresenter<LC, SC>
    abstract override val input: LC
    abstract override val expected: Layout
}
