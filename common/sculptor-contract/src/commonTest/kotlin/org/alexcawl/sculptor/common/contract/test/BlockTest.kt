package org.alexcawl.sculptor.common.contract.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.Block
import org.alexcawl.sculptor.common.contract.ContractTest
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.contract.mock.Mock
import org.alexcawl.sculptor.common.contract.mock.MockModifierContract
import org.alexcawl.sculptor.common.contract.mock.MockStateContract

class BlockTest : ContractTest<Block<MockStateContract>>() {
    override val serializer: KSerializer<Block<MockStateContract>>
        get() = Block.serializer(MockStateContract.serializer())

    override val value: Block<MockStateContract>
        get() = Block(
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
