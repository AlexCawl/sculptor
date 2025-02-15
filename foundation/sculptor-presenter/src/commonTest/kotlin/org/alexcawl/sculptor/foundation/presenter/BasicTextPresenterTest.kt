package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.foundation.contract.layout.BasicTextLayoutContract
import org.alexcawl.sculptor.foundation.layout.BasicTextLayout

class BasicTextPresenterTest : BasePresenterTest<BasicTextLayoutContract, BasicTextLayout>() {
    override val presenterScope: PresenterScope
        get() = TODO("Not yet implemented")
    override val presenter: BasicTextPresenter = BasicTextPresenter()

    override fun transformationTest() {
        TODO("Not yet implemented")
    }
}