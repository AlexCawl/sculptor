package org.alexcawl.sculptor.common.presenter

import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.test.assertEquals

abstract class StatePresenterTest<SC : StateContract> : PresenterTest<SC, Layout>() {
    abstract override val presenter: StatePresenter<SC>
    abstract override val input: SC
    abstract override val expected: Layout

    @OptIn(InternalSculptorApi::class)
    final override fun transformationTest() {
        val bundle = StatePresenter.Bundle(
            id = "test@${input.id.value}",
            modifiers = Modifier,
            state = input,
        )
        val actual = presenter.internalTransform(scope = presenterScope, input = bundle)
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Transformation failed for $input",
        )
    }
}
