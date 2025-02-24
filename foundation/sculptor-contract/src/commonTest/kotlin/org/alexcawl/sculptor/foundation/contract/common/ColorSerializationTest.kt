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
                color = Color(content = "ff000000"),
                colorString = "\"ff000000\"",
            ),
            State(
                color = Color(content = "ffffff"),
                colorString = "\"ffffff\"",
            ),
            State(
                color = Color(content = "ff0000"),
                colorString = "\"ff0000\"",
            ),
            State(
                color = Color(content = "00ff00"),
                colorString = "\"00ff00\"",
            ),
            State(
                color = Color(content = "0000ff"),
                colorString = "\"0000ff\"",
            ),
            State(
                color = Color(content = "ffff00"),
                colorString = "\"ffff00\"",
            ),
            State(
                color = Color(content = "ff00ffff"),
                colorString = "\"ff00ffff\"",
            ),
        )
    }
}
