package org.alexcawl.sculptor.common.contract.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.ContractTest
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.contract.mock.Mock
import org.alexcawl.sculptor.common.contract.mock.MockModifierContract
import org.alexcawl.sculptor.common.contract.mock.MockStateContract

class BlockTest : ContractTest<Section>() {
    override val serializer: KSerializer<Section>
        get() = Section.serializer()

    override val value: Section
        get() = Section(
            id = "testId".id,
            forcedState = "testState".id,
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
                        "type": "mock@state",
                        "id": "testState",
                        "value": {
                            "data": "testValue"
                        }
                    }
                ],
                "forced": "testState"
            }
        """.trimIndent()
}
