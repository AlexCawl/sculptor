package org.alexcawl.sculptor.common.contract.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.StateContractTest
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.contract.mock.Mock
import org.alexcawl.sculptor.common.contract.mock.MockStateContract

class StateTest : StateContractTest<MockStateContract>() {
    override val serializer: KSerializer<MockStateContract> = MockStateContract.serializer()

    override val value: MockStateContract = MockStateContract(
        id = "testState".id,
        value = Mock(
            data = "testValue",
        ),
    )

    override val string: String
        get() = """
            {
                "id": "testState",
                "value": {
                    "data": "testValue"
                }
            }
        """.trimIndent()
}
