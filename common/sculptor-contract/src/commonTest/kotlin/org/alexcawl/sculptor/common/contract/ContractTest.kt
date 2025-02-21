package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.sculptor.common.contract.mock.MockLayoutContract
import org.alexcawl.sculptor.common.contract.mock.MockModifierContract
import org.alexcawl.sculptor.common.contract.mock.MockStateContract
import org.alexcawl.sculptor.common.contract.mock.MockValueContract
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class ContractTest<C> {
    protected open val format: StringFormat = Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(LayoutContract::class) {
                subclass(MockLayoutContract::class)
            }
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
