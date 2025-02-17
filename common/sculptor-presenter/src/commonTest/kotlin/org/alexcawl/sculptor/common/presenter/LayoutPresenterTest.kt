package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.test.assertEquals

abstract class LayoutPresenterTest<LC : LayoutContract, SC : StateContract> : PresenterTest<LC, Layout> {
    abstract override val presenter: LayoutPresenter<LC, SC>

    abstract val input: LC
    abstract val expected: Layout

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

/*
    @OptIn(InternalSculptorApi::class)
    override val presenterScope: PresenterScope = PresenterScope(
        presenterProvider = { _, _ -> error("Mock") },
        layoutProvider = { _ -> error("Mock") },
        valueProvider = { _ -> error("Mock") },
    )

    override val presenter = layoutPresenter(
        contract = TestLayoutContract::class
    ) { id, modifier, state: TestStateContract ->
        with(state) {
            TestLayout(
                id = id,
                modifier = modifier,
                testValue = testValue,
            )
        }
    }

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
 */