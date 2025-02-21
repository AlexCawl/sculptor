package org.alexcawl.sculptor.common.contract.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.LayoutContractTest
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.contract.mock.Mock
import org.alexcawl.sculptor.common.contract.mock.MockLayoutContract
import org.alexcawl.sculptor.common.contract.mock.MockModifierContract
import org.alexcawl.sculptor.common.contract.mock.MockStateContract

class LayoutTest : LayoutContractTest<MockLayoutContract>() {
    override val serializer: KSerializer<MockLayoutContract> = MockLayoutContract.serializer()

    override val value: MockLayoutContract = MockLayoutContract(
        id = "testId".id,
        state = "testState".id,
        modifiers = listOf(
            MockModifierContract(
                value = Mock(
                    data = "testValue",
                ),
            ),
        ),
        states = listOf(
            MockStateContract(
                id = "testState".id,
                modifiers = listOf(
                    MockModifierContract(
                        value = Mock(
                            data = "testValue"
                        ),
                    ),
                ),
                value = Mock(
                    data = "testValue",
                ),
            ),
        ),
    )

    override val string: String
        get() = """
            {
                "id": "testId",
                "state": "testState",
                "modifiers": [
                    {
                        "type": "mock@modifier",
                        "value": {
                            "data": "testValue"
                        }
                    }
                ],
                "states": [
                    {
                        "id": "testState",
                        "modifiers": [
                            {
                                "type": "mock@modifier",
                                "value": {
                                    "data": "testValue"
                                }
                            }
                        ],
                        "value": {
                            "data": "testValue"
                        }
                    }
                ]
            }
        """.trimIndent()
}
