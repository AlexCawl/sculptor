package org.alexcawl.sculptor.common.contract.value

import kotlinx.serialization.encodeToString
import org.alexcawl.sculptor.common.contract.BooleanValueContract
import org.alexcawl.sculptor.common.contract.id
import kotlin.test.assertEquals

class BooleanValueSerializationTest : ValueSerializationTest<BooleanValueContract>() {
    override val value: BooleanValueContract = BooleanValueContract(
        id = "id".id,
        value = true,
    )

    override val string: String
        get() = """
            {
                "id": "id",
                "value": true
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
        val actual = format.decodeFromString<BooleanValueContract>(string)
        val expected = value
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Deserialization failed"
        )
    }
}