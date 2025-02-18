package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.foundation.contract.common.Dp
import org.alexcawl.sculptor.foundation.presenter.CommonPresenterTest
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import androidx.compose.ui.unit.Dp as ComposeDp
import org.alexcawl.sculptor.foundation.contract.common.Dp as SculptorDp

@RunWith(Parameterized::class)
class DpPresenterTest(state: State) : CommonPresenterTest<SculptorDp, ComposeDp>() {
    override val presenter: DpPresenter = DpPresenter()
    override val input: Dp = state.input
    override val expected: ComposeDp = state.expected

    data class State(
        val input: SculptorDp,
        val expected: ComposeDp,
    )

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun run(): List<State> = listOf(
            State(
                input = SculptorDp.Hairline,
                expected = ComposeDp.Hairline,
            ),
            State(
                input = SculptorDp.Infinity,
                expected = ComposeDp.Infinity,
            ),
            State(
                input = SculptorDp.Unspecified,
                expected = ComposeDp.Unspecified,
            ),
            State(
                input = SculptorDp.Number(value = 10f),
                expected = ComposeDp(value = 10f),
            ),
            State(
                input = SculptorDp.Number(value = 12345.67f),
                expected = ComposeDp(value = 12345.67f),
            ),
        )
    }
}
