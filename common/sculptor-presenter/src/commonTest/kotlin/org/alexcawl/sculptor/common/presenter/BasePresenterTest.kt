package org.alexcawl.sculptor.common.presenter

import org.junit.Test

abstract class BasePresenterTest<I : Any, O : Any> {
    abstract val presenterScope: PresenterScope
    abstract val presenter: Presenter<I, O>

    @Test
    abstract fun transformationTest()
}
