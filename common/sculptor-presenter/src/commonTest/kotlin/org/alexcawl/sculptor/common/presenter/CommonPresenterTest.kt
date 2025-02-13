package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.core.InternalSculptorApi
import kotlin.test.assertEquals

class CommonPresenterTest : BasePresenterTest<Int, String>() {
    @OptIn(InternalSculptorApi::class)
    override val presenterScope: PresenterScope = PresenterScope(
        delegateTransform = { _, _, _ -> Any() }
    )

    override val presenter = commonPresenter(
        input = Int::class,
        output = String::class
    ) { input: Int ->
        input.toString()
    }

    @OptIn(InternalSculptorApi::class)
    override fun transformationTest() {
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

        val transformed = presenter.internalTransform(presenterScope, input)

        assertEquals(
            expected = output,
            actual = transformed,
            message = "Presenter output is not correct"
        )
    }
}
