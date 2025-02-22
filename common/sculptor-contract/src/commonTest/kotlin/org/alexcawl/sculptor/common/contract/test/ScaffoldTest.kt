package org.alexcawl.sculptor.common.contract.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.ContractTest
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.contract.mock.Mock
import org.alexcawl.sculptor.common.contract.mock.MockStateContract
import org.alexcawl.sculptor.common.contract.mock.MockValueContract

class ScaffoldTest : ContractTest<Scaffold>() {
    override val serializer: KSerializer<Scaffold> = Scaffold.serializer()

    override val value: Scaffold = Scaffold(
        values = listOf(
            MockValueContract(
                id = "testValue".id,
                value = Mock(
                    data = "testValue",
                ),
            ),
        ),
        sections = listOf(
            Section(
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
                "values": [
                    {
                        "type": "mock@value",
                        "id": "testValue",
                        "value": {
                            "data": "testValue"
                        }
                    }
                ],
                "sections": [
                    {
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
