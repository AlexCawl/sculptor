package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import kotlin.test.assertEquals

abstract class LayoutPresenterTest<LC : LayoutContract, SC : StateContract> : PresenterTest<LC, Layout>() {
    abstract override val presenter: LayoutPresenter<LC, SC>

    protected abstract val input: LC
    protected abstract val expected: Layout

    @OptIn(InternalSculptorApi::class)
    final override fun transformationTest() {
        val actual = presenter.internalTransform(presenterScope, input)
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Transformation failed for $input"
        )
    }
}