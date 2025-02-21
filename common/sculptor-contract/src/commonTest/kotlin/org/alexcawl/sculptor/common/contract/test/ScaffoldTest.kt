package org.alexcawl.sculptor.common.contract.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.ContractTest
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.contract.mock.Mock
import org.alexcawl.sculptor.common.contract.mock.MockLayoutContract
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
            MockLayoutContract(
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
                "rootLayoutId": "root",
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
                        "type": "mock@layout",
                        "id": "root",
                        "state": "state1",
                        "modifiers": [],
                        "states": [
                            {
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
