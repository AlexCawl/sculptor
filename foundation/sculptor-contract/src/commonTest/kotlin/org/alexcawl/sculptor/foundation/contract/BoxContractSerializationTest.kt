package org.alexcawl.sculptor.foundation.contract

import kotlinx.serialization.encodeToString
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.foundation.contract.layout.BoxLayoutContract
import org.alexcawl.sculptor.foundation.contract.layout.BoxStateContract
import org.alexcawl.sculptor.foundation.contract.property.Alignment
import kotlin.test.assertEquals

class BoxContractSerializationTest : BaseSerializationTest<BoxLayoutContract>(){
    override val value: BoxLayoutContract
        get() = BoxLayoutContract(
            id = "id".id,
            modifiers = listOf(),
            state = "state1".id,
            states = listOf(
                BoxStateContract(
                    id = "state1".id,
                    modifiers = listOf(),
                    contentAlignment = Alignment.Center,
                    propagateMinConstraints = true,
                    content = listOf("content1".id, "content2".id),
                )
            )
        )

    override val string: String
        get() = """
            {
                "id": "id",
                "state": "state1",
                "modifiers": [],
                "states": [
                    {
                        "id": "state1",
                        "modifiers": [],
                        "content_alignment": {
                            "type": "center"
                        },
                        "propagate_min_constraints": true,
                        "content": [
                            "content1",
                            "content2"
                        ]
                    }
                ]
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
        val actual = format.decodeFromString<BoxLayoutContract>(string)
        val expected = value
        assertEquals(
            expected = expected,
            actual = actual,
            message = "Deserialization failed"
        )
    }
}
