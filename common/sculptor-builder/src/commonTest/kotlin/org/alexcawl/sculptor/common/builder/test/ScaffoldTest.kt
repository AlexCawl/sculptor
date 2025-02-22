package org.alexcawl.sculptor.common.builder.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.builder.BuilderTest
import org.alexcawl.sculptor.common.builder.mock.Mock
import org.alexcawl.sculptor.common.builder.mock.MockModifierContract
import org.alexcawl.sculptor.common.builder.mock.MockStateContract
import org.alexcawl.sculptor.common.builder.scaffold
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.Style
import org.alexcawl.sculptor.common.contract.id

class ScaffoldTest : BuilderTest<Scaffold>() {
    override val serializer: KSerializer<Scaffold> = Scaffold.serializer()

    override val value: Scaffold = scaffold {
        section("root", forcedState = "state2") {
            state {
                MockStateContract(
                    id = "state1".id,
                    value = Mock(
                        data = "state1",
                    ),
                )
            }
            state {
                MockStateContract(
                    id = "state2".id,
                    value = Mock(
                        data = "state2",
                    ),
                )
            }
        }
        section(
            id = "not-root",
            modifiers = buildList {
                add(MockModifierContract(value = Mock(data = "not-root")))
            },
        ) {
            state {
                MockStateContract(
                    id = "state3".id,
                    value = Mock(
                        data = "state3",
                    ),
                )
            }
        }
        singleSection("lol-section") {
            MockStateContract(
                id = "state4".id,
                value = Mock(
                    data = "state4",
                ),
            )
        }
    }

    override val string: String
        get() = """
            {
                "values": [],
                "sections": [
                    {
                        "id": "root",
                        "modifiers": [],
                        "states": [
                            {
                                "type": "mock@state",
                                "id": "state1",
                                "value": {
                                    "data": "state1"
                                }
                            },
                            {
                                "type": "mock@state",
                                "id": "state2",
                                "value": {
                                    "data": "state2"
                                }
                            }
                        ],
                        "forced": "state2"
                    },
                    {
                        "id": "not-root",
                        "modifiers": [
                            {
                                "type": "mock@modifier",
                                "value": {
                                    "data": "not-root"
                                }
                            }
                        ],
                        "states": [
                            {
                                "type": "mock@state",
                                "id": "state3",
                                "value": {
                                    "data": "state3"
                                }
                            }
                        ]
                    },
                    {
                        "id": "lol-section",
                        "modifiers": [],
                        "states": [
                            {
                                "type": "mock@state",
                                "id": "state4",
                                "value": {
                                    "data": "state4"
                                }
                            }
                        ]
                    }
                ]
            }
        """.trimIndent()
}
