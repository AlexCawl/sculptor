package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.foundation.presenter.CommonPresenterTest
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import androidx.compose.ui.unit.Dp as ComposeDp
import androidx.compose.ui.unit.DpSize as ComposeDpSize
import org.alexcawl.sculptor.foundation.contract.common.Dp as SculptorDp
import org.alexcawl.sculptor.foundation.contract.common.DpSize as SculptorDpSize

@RunWith(Parameterized::class)
class DpSizePresenterTest(state: State) : CommonPresenterTest<SculptorDpSize, ComposeDpSize>() {
    override val presenter: DpSizePresenter = DpSizePresenter()
    override val input: SculptorDpSize = state.input
    override val expected: ComposeDpSize = state.expected

    data class State(
        val input: SculptorDpSize,
        val expected: ComposeDpSize,
    )

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun run(): List<State> = listOf(
            State(
                input = SculptorDpSize.Zero,
                expected = ComposeDpSize.Zero,
            ),
            State(
                input = SculptorDpSize.Unspecified,
                expected = ComposeDpSize.Unspecified,
            ),
            State(
                input = SculptorDpSize.Content(
                    width = SculptorDp.Number(value = 100f),
                    height = SculptorDp.Number(value = 200f),
                ),
                expected = ComposeDpSize(
                    width = ComposeDp(value = 100f),
                    height = ComposeDp(value = 200f),
                ),
            )
        )
    }
}
