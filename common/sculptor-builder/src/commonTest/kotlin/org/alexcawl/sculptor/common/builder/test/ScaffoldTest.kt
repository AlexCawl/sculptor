package org.alexcawl.sculptor.common.builder.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.builder.BuilderTest
import org.alexcawl.sculptor.common.builder.mock.Mock
import org.alexcawl.sculptor.common.builder.mock.MockModifierContract
import org.alexcawl.sculptor.common.builder.mock.builder.mockLayout
import org.alexcawl.sculptor.common.builder.mock.builder.mockState
import org.alexcawl.sculptor.common.builder.mock.builder.mockValue
import org.alexcawl.sculptor.common.builder.scaffold
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.Style

class ScaffoldTest : BuilderTest<Scaffold>() {
    override val serializer: KSerializer<Scaffold> = Scaffold.serializer()

    override val value: Scaffold = scaffold {
        mockValue(
            id = "testValue",
            value = Mock(
                data = "testValue"
            ),
        )
        mockLayout(
            id = "root",
            modifier = Style plus MockModifierContract(value = Mock(data = "testValue"))
        ) {
            mockState(
                id = "testState",
                modifier = Style plus MockModifierContract(value = Mock(data = "testValue"))
            ) {
                testValue = Mock(data = "testValue")
            }
        }
    }

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
                        "type": "mock@layout",
                        "id": "root",
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
                ]
            }
        """.trimIndent()
}
