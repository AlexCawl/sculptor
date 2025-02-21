package org.alexcawl.sculptor.common.contract.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.ModifierContractTest
import org.alexcawl.sculptor.common.contract.mock.Mock
import org.alexcawl.sculptor.common.contract.mock.MockModifierContract

class ModifierTest : ModifierContractTest<MockModifierContract>() {
    override val serializer: KSerializer<MockModifierContract> = MockModifierContract.serializer()

    override val value: MockModifierContract = MockModifierContract(
        value = Mock(
            data = "testValue",
        ),
    )

    override val string: String
        get() = """
            {
                "value": {
                    "data": "testValue"
                }
            }
        """.trimIndent()
}
