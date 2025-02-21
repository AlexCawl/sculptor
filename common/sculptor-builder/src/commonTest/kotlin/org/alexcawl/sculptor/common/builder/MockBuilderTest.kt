package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.builder.mock.MockModifierContract
import org.alexcawl.sculptor.common.builder.mock.MockProperty
import org.alexcawl.sculptor.common.builder.mock.builder.mockLayout
import org.alexcawl.sculptor.common.builder.mock.builder.mockState
import org.alexcawl.sculptor.common.builder.mock.builder.mockValue
import org.alexcawl.sculptor.common.contract.ModifierList
import org.alexcawl.sculptor.common.contract.Scaffold

class MockBuilderTest : BuilderTest() {
    override val value: Scaffold
        get() = scaffold {
            mockValue(
                id = "value1",
                value = MockProperty(testValue = "Hello world!")
            )
            mockLayout("layout1") {
                addModifier(
                    MockModifierContract(testValue = "modifier here")
                )
                mockState("state1") {
                    testValue = "testValue"
                }
                mockState("state2") {
                    testValue = "test value 2"
                }
                mockState(
                    id ="state3",
                    modifier = ModifierList
                            plus MockModifierContract(testValue = "other modifier here")
                            plus MockModifierContract(testValue = "and other modifier here")
                            plus MockModifierContract(testValue = "idk lol")
                ) {
                    testValue = "test value 3"
                }
            }
        }

    override val string: String
        get() = """
            {
                "rootLayoutId": "layout1",
                "values": [
                    {
                        "type": "mock@value",
                        "id": "value1",
                        "value": {
                            "test_value": "Hello world!"
                        }
                    }
                ],
                "layouts": [
                    {
                        "type": "mock@layout",
                        "id": "layout1",
                        "state": "state1",
                        "modifiers": [
                            {
                                "type": "mock@modifier",
                                "testValue": "modifier here"
                            }
                        ],
                        "states": [
                            {
                                "id": "state1",
                                "modifiers": [],
                                "testValue": "testValue"
                            },
                            {
                                "id": "state2",
                                "modifiers": [],
                                "testValue": "test value 2"
                            },
                            {
                                "id": "state3",
                                "modifiers": [
                                    {
                                        "type": "mock@modifier",
                                        "testValue": "other modifier here"
                                    },
                                    {
                                        "type": "mock@modifier",
                                        "testValue": "and other modifier here"
                                    },
                                    {
                                        "type": "mock@modifier",
                                        "testValue": "idk lol"
                                    }
                                ],
                                "testValue": "test value 3"
                            }
                        ]
                    }
                ]
            }
        """.trimIndent()
}
