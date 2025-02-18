package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.foundation.contract.SerializationTest
import org.alexcawl.sculptor.foundation.contract.common.Alignment

class BoxContractSerializationTest : SerializationTest<BoxLayoutContract>(){
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

    override val serializer: KSerializer<BoxLayoutContract> = BoxLayoutContract.serializer()
}
