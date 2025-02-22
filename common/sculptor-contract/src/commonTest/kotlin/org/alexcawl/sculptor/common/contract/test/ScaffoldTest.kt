package org.alexcawl.sculptor.common.contract.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.Block
import org.alexcawl.sculptor.common.contract.ContractTest
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.contract.mock.Mock
import org.alexcawl.sculptor.common.contract.mock.MockStateContract
import org.alexcawl.sculptor.common.contract.mock.MockValueContract

class ScaffoldTest : ContractTest<Scaffold>() {
    override val serializer: KSerializer<Scaffold> = Scaffold.serializer()

    override val value: Scaffold = Scaffold(
        rootLayoutId = "root".id,
        values = listOf(
            MockValueContract(
                id = "testValue".id,
                value = Mock(
                    data = "testValue",
                ),
            ),
        ),
        layouts = listOf(
            Block(
                id = "root".id,
                state = "state1".id,
                modifiers = emptyList(),
                states = listOf(
                    MockStateContract(
                        id = "state1".id,
                        modifiers = emptyList(),
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
                "root_layout_id": "root",
                "values": [
                    {
                        "type": "mock@value",
                        "id": "testValue",
                        "value": {
                            "data": "testValue"
                        }
                    }
                ],
                "layouts": [
                    {
                        "id": "root",
                        "state": "state1",
                        "modifiers": [],
                        "states": [
                            {
                                "type": "mock@state",
                                "id": "state1",
                                "modifiers": [],
                                "value": {
                                    "data": "testValue"
                                }
                            }
                        ]
                    }
                ]
            }
        """.trimIndent()
}
