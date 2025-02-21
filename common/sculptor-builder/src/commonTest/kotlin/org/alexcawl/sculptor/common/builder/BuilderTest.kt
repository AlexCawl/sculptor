package org.alexcawl.sculptor.common.builder

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.sculptor.common.builder.mock.MockLayoutContract
import org.alexcawl.sculptor.common.builder.mock.MockModifierContract
import org.alexcawl.sculptor.common.builder.mock.MockStateContract
import org.alexcawl.sculptor.common.builder.mock.MockValueContract
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.contract.ValueContract
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class BuilderTest {
    private val format: Json = Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(ModifierContract::class) {
                subclass(MockModifierContract::class)
            }
            polymorphic(LayoutContract::class) {
                subclass(MockLayoutContract::class)
            }
            polymorphic(StateContract::class) {
                subclass(MockStateContract::class)
            }
            polymorphic(ValueContract::class) {
                subclass(MockValueContract::class)
            }
        }
    }

    abstract val value: Scaffold

    abstract val string: String

    @Test
    fun serializationTest() {
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
    fun deserializationTest() {
        val actual = format.decodeFromString<Scaffold>(string)
        val expected = value
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Deserialization failed"
        )
    }
}
