package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.foundation.contract.SerializationTest
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DpSerializationTest(state: State) : SerializationTest<Dp>() {
    override val value: Dp = state.value
    override val string: String = state.string
    override val serializer: KSerializer<Dp> = Dp.serializer()

    data class State(
        val value: Dp,
        val string: String,
    )

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data(): List<State> = listOf(
            State(value = Dp.Hairline, string = "\"hairline\""),
            State(value = Dp.Infinity, string = "\"infinity\""),
            State(value = Dp.Unspecified, string = "\"unspecified\""),
            State(value = Dp.Number(value = 1f), string = "\"1.0\""),
            State(value = Dp.Number(value = 1.5f), string = "\"1.5\""),
        )
    }
}
