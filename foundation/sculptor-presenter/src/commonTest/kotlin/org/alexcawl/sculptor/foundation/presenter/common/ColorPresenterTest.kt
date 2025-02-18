package org.alexcawl.sculptor.foundation.presenter.common

import org.alexcawl.sculptor.foundation.presenter.CommonPresenterTest
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


import androidx.compose.ui.graphics.Color as ComposeColor
import org.alexcawl.sculptor.foundation.contract.common.Color as SculptorColor

@RunWith(Parameterized::class)
class ColorPresenterTest(state: State) : CommonPresenterTest<SculptorColor, ComposeColor>() {
    override val presenter: ColorPresenter = ColorPresenter()
    override val input: SculptorColor = state.input
    override val expected: ComposeColor = state.expected

    data class State(
        val input: SculptorColor,
        val expected: ComposeColor,
    )

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun run(): List<State> = listOf(
            State(
                input = SculptorColor("000000"),
                expected = ComposeColor.Black,
            ),
            State(
                input = SculptorColor("FFFFFF"),
                expected = ComposeColor.White,
            ),
            State(
                input = SculptorColor("FF0000"),
                expected = ComposeColor.Red,
            ),
            State(
                input = SculptorColor("00FF00"),
                expected = ComposeColor.Green,
            ),
            State(
                input = SculptorColor("0000FF"),
                expected = ComposeColor.Blue,
            ),
            State(
                input = SculptorColor("FFFF00"),
                expected = ComposeColor.Yellow,
            ),
            State(
                input = SculptorColor("00FFFF"),
                expected = ComposeColor.Cyan,
            ),
            State(
                input = SculptorColor("FF00FF"),
                expected = ComposeColor.Magenta,
            ),
        )
    }
}
