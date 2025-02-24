package org.alexcawl.sculptor.common.contract.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.ContractTest
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.contract.mock.Mock
import org.alexcawl.sculptor.common.contract.mock.MockStateContract

class ScaffoldTest : ContractTest<Scaffold>() {
    override val serializer: KSerializer<Scaffold> = Scaffold.serializer()

    override val value: Scaffold = Scaffold(
        sections = listOf(
            Section.Composite(
                id = "root".id,
                forcedState = "state1".id,
                modifiers = emptyList(),
                states = listOf(
                    MockStateContract(
                        id = "state1".id,
                        value = Mock(
                            data = "testValue"
                        ),
                    ),
                ),
            ),
        ),
    )

    override val string: String
        get() = """
            {
                "sections": [
                    {
                        "type": "composite",
                        "id": "root",
                        "modifiers": [],
                        "states": [
                            {
                                "type": "mock@state",
                                "id": "state1",
                                "value": {
                                    "data": "testValue"
                                }
                            }
                        ],
                        "forced": "state1"
                    }
                ]
            }
        """.trimIndent()
}
