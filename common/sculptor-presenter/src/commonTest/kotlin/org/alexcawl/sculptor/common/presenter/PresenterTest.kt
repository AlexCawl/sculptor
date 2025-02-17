package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi

interface PresenterTest<I : Any, O : Any> {
    @OptIn(InternalSculptorApi::class)
    val presenterScope: PresenterScope
        get() = PresenterScope(
            presenterProvider = { _, _ -> error("Mock") },
            layoutProvider = { _ -> error("Mock") },
            valueProvider = { _ -> error("Mock") },
        )

    val presenter: Presenter<I, O>

    fun transformationTest()
}
