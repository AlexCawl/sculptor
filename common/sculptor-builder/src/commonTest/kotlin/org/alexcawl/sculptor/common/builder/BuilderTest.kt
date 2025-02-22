package org.alexcawl.sculptor.common.builder

import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.sculptor.common.builder.mock.MockModifierContract
import org.alexcawl.sculptor.common.builder.mock.MockStateContract
import org.alexcawl.sculptor.common.builder.mock.MockValueContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.contract.ValueContract
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class BuilderTest<C> {
    protected open val format: StringFormat = Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(StateContract::class) {
                subclass(MockStateContract::class)
            }
            polymorphic(ModifierContract::class) {
                subclass(MockModifierContract::class)
            }
            polymorphic(ValueContract::class) {
                subclass(MockValueContract::class)
            }
        }
    }

    abstract val value: C
    abstract val serializer: KSerializer<C>
    abstract val string: String

    @Test
    fun serializationTest() {
        val actual: String = format.encodeToString(serializer, value)
        val expected: String = string
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Serialization failed",
        )
    }

    @Test
    fun deserializationTest() {
        val actual: C = format.decodeFromString(serializer, string)
        val expected: C = value
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Deserialization failed",
        )
    }
}
