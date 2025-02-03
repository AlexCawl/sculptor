package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.test.Test
import kotlin.test.assertEquals

class PresenterTest {
    @OptIn(InternalSculptorApi::class)
    @Test
    fun `Check if presenter can be matched with input and output markers`() {
        val presenter: Presenter<Int, String> = PresenterMock()
        val presenterScope = PresenterScope(delegateTransform = { _, _, _ -> Any() })
        val input = 5
        val output = "5"

        assertEquals(
            expected = presenter.input,
            actual = input::class,
            message = "Presenter input type is not Int"
        )

        assertEquals(
            expected = presenter.output,
            actual = output::class,
            message = "Presenter output type is not String"
        )

        val transformed = presenter.transform(presenterScope, input)

        assertEquals(
            expected = output,
            actual = transformed,
            message = "Presenter output is not correct"
        )
    }
}

private class PresenterMock : Presenter<Int, String>() {
    override val input = Int::class
    override val output = String::class

    override fun PresenterScope.transform(input: Int): String = input.toString()
}
