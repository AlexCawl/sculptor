package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.junit.Test

abstract class BasePresenterTest<I : Any, O : Any> {
    abstract val presenterScope: PresenterScope
    abstract val presenter: Presenter<I, O>

    @Test
    abstract fun transformationTest()
}
