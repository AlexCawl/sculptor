package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.Block
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.foundation.contract.SerializationTest
import org.alexcawl.sculptor.foundation.contract.common.Alignment

class BoxStateSerializationTest : SerializationTest<Block<BoxState>>(){
    override val serializer: KSerializer<Block<BoxState>> = Block.serializer(BoxState.serializer())

    override val value: Block<BoxState>
        get() = Block(
            id = "id".id,
            modifiers = listOf(),
            state = "state1".id,
            states = listOf(
                BoxState(
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
}
