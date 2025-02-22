package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.foundation.contract.SerializationTest
import org.alexcawl.sculptor.foundation.contract.common.Alignment

class BoxStateSerializationTest : SerializationTest<Section>(){
    override val serializer: KSerializer<Section> = Section.serializer()

    override val value: Section
        get() = Section(
            id = "id".id,
            modifiers = listOf(),
            forcedState = "state1".id,
            states = listOf(
                BoxState(
                    id = "state1".id,
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
                "modifiers": [],
                "states": [
                    {
                        "type": "box@state",
                        "id": "state1",
                        "content_alignment": {
                            "type": "center"
                        },
                        "propagate_min_constraints": true,
                        "content": [
                            "content1",
                            "content2"
                        ]
                    }
                ],
                "forced": "state1"
            }
        """.trimIndent()
}
