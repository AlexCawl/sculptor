package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.contract.layout.StateContract
import kotlin.test.Test
import kotlin.test.assertEquals

class SculptorScreenTest : BaseSerializationTest<SculptorScreen>() {
    override val format: Json = Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(StateContract::class) {
                subclass(TestState::class)
            }
        }
    }

    override val value: SculptorScreen
        get() = SculptorScreen(
            rootLayoutId = "root".id,
            values = emptyList(),
            layout = listOf(
                LayoutContract(
                    id = "root".id,
                    state = "state1".id,
                    modifiers = emptyList(),
                    states = listOf(
                        TestState(
                            id = "state1".id,
                            modifiers = emptyList(),
                            testValue = "testValue"
                        )
                    )
                )
            ),
        )

    override val string: String
        get() = """
            {
                "rootLayoutId": "root",
                "values": [],
                "layout": [
                    {
                        "id": "root",
                        "state": "state1",
                        "modifiers": [],
                        "states": [
                            {
                                "type": "state@test",
                                "id": "state1",
                                "modifiers": [],
                                "testValue": "testValue"
                            }
                        ]
                    }
                ]
            }
        """.trimIndent()

    @Test
    override fun serializationTest() {
        val actual = format.encodeToString(value)
        val expected = string
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Serialization failed"
        )
    }

    @Test
    override fun deserializationTest() {
        val actual = format.decodeFromString<SculptorScreen>(string)
        val expected = value
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Deserialization failed"
        )
    }
}

@Serializable
@SerialName("state@test")
private data class TestState(
    override val id: Identifier,
    override val modifiers: List<ModifierContract>,
    val testValue: String,
) : StateContract
