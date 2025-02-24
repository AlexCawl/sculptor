package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class PresenterTest<I : Any, O : Any> {
    @OptIn(InternalSculptorApi::class)
    protected open val presenterScope: PresenterScope
        get() = PresenterScope(
            presenters = emptyList(),
            sections = emptyList(),
        )

    abstract val presenter: Presenter<I, O>
    abstract val input: I
    abstract val expected: O

    @OptIn(InternalSculptorApi::class)
    @Test
    open fun transformationTest() {
        val actual = presenter.internalTransform(scope = presenterScope, input = input)
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Transformation failed for $input",
        )
    }
}
