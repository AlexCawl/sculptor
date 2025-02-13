package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.presenter.utils.TestLayout
import org.alexcawl.sculptor.common.presenter.utils.TestLayoutContract
import org.alexcawl.sculptor.common.presenter.utils.TestStateContract
import kotlin.test.assertEquals

class LayoutPresenterTest : BasePresenterTest<TestLayoutContract, TestLayout>() {
    @OptIn(InternalSculptorApi::class)
    override val presenterScope: PresenterScope = PresenterScope(
        delegateTransform = { input, output, _ ->
            when {
                input is ModifierContract && output is Modifier -> Modifier
                else -> error("Unknown input and output types")
            }
        }
    )

    override val presenter = layoutPresenter(
        contract = TestLayoutContract::class,
        layout = TestLayout::class
    ) { id, modifier, state: TestStateContract ->
        with(state) {
            TestLayout(
                id = id,
                modifier = modifier,
                testValue = testValue,
            )
        }
    }

    @OptIn(InternalSculptorApi::class)
    override fun transformationTest() {
        val input = TestLayoutContract(
            id = "root".id,
            state = "state1".id,
            modifiers = emptyList(),
            states = listOf(
                TestStateContract(
                    id = "state1".id,
                    modifiers = emptyList(),
                    testValue = "testValue"
                )
            )
        )
        val output = TestLayout(
            id = "root@state1",
            modifier = Modifier,
            testValue = "testValue"
        )

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
