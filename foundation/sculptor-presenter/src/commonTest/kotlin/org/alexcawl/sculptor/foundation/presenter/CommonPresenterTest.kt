package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.presenter.CommonPresenter

abstract class CommonPresenterTest<I : Any, O : Any> : PresenterTest<I, O>() {
    abstract override val presenter: CommonPresenter<I, O>

    abstract override val input: I
    abstract override val expected: O
}
