package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.layout.Layout
import org.junit.Test

abstract class BasePresenterTest<C : Any> {
    abstract val presenterScope: PresenterScope
    abstract val presenter: Presenter<C, Layout>

    @Test
    abstract fun transformationTest()
}
