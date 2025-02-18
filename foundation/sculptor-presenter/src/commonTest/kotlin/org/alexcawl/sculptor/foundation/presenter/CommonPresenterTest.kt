package org.alexcawl.sculptor.foundation.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.presenter.CommonPresenter
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class CommonPresenterTest<I : Any, O : Any> : PresenterTest<I, O> {
    abstract override val presenter: CommonPresenter<I, O>

    abstract val input: I
    abstract val expected: O

    @OptIn(InternalSculptorApi::class)
    @Test
    final override fun transformationTest() {
        val actual = presenter.internalTransform(presenterScope, input)
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Transformation failed for $input",
        )
    }
}
