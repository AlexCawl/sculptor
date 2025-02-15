package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
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

class ScaffoldTest : BaseSerializationTest<Scaffold>() {
    override val format: Json = Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(LayoutContract::class) {
                subclass(TestLayout::class)
            }
            polymorphic(StateContract::class) {
                subclass(TestState::class)
            }
        }
    }

    override val value: Scaffold
        get() = Scaffold(
            rootLayoutId = "root".id,
            values = emptyList(),
            layouts = listOf(
                TestLayout(
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
                        "type": "contract@test",
                        "id": "root",
                        "state": "state1",
                        "modifiers": [],
                        "states": [
                            {
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
        println(actual)
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Serialization failed"
        )
    }

    @Test
    override fun deserializationTest() {
        val actual = format.decodeFromString<Scaffold>(string)
        val expected = value
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Deserialization failed"
        )
    }
}

@Serializable
@SerialName("contract@test")
private data class TestLayout(
    override val id: Identifier,
    override val state: Identifier,
    override val modifiers: List<ModifierContract>,
    override val states: List<TestState>
) : LayoutContract

@Serializable
@SerialName("state@test")
private data class TestState(
    override val id: Identifier,
    override val modifiers: List<ModifierContract>,
    val testValue: String,
) : StateContract
