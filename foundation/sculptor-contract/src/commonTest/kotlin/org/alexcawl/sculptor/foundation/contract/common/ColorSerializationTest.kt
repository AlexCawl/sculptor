package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.foundation.contract.SerializationTest
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ColorSerializationTest(state: State) : SerializationTest<Color>() {
    override val value: Color = state.color
    override val string: String = state.colorString
    override val serializer: KSerializer<Color> = Color.serializer()

    data class State(
        val color: Color,
        val colorString: String,
    )

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun run(): List<State> = listOf(
            State(
                color = Color("ff000000"),
                colorString = "\"ff000000\"",
            ),
            State(
                color = Color("ffffff"),
                colorString = "\"ffffffff\"",
            ),
            State(
                color = Color("ff0000"),
                colorString = "\"ffff0000\"",
            ),
            State(
                color = Color("00ff00"),
                colorString = "\"ff00ff00\"",
            ),
            State(
                color = Color("0000ff"),
                colorString = "\"ff0000ff\"",
            ),
            State(
                color = Color("ffff00"),
                colorString = "\"ffffff00\"",
            ),
            State(
                color = Color("00ffff"),
                colorString = "\"ff00ffff\"",
            ),
        )
    }
}
