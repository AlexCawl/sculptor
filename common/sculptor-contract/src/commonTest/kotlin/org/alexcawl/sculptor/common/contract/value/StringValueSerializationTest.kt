package org.alexcawl.sculptor.common.contract.value

import kotlinx.serialization.encodeToString
import org.alexcawl.sculptor.common.contract.StringValueContract
import org.alexcawl.sculptor.common.contract.id
import kotlin.test.assertEquals

class StringValueSerializationTest : ValueSerializationTest<StringValueContract>() {
    override val value: StringValueContract = StringValueContract(
        id = "id".id,
        value = "value",
    )

    override val string: String
        get() = """
            {
                "id": "id",
                "value": "value"
            }
        """.trimIndent()

    override fun serializationTest() {
        val actual = format.encodeToString(value)
        val expected = string
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Serialization failed"
        )
    }

    override fun deserializationTest() {
        val actual = format.decodeFromString<StringValueContract>(string)
        val expected = value
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Deserialization failed"
        )
    }
}
