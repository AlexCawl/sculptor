package org.alexcawl.sculptor.common.contract.test

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.contract.ValueContractTest
import org.alexcawl.sculptor.common.contract.id
import org.alexcawl.sculptor.common.contract.mock.Mock
import org.alexcawl.sculptor.common.contract.mock.MockValueContract

class ValueTest : ValueContractTest<MockValueContract>() {
    override val serializer: KSerializer<MockValueContract> = MockValueContract.serializer()

    override val value: MockValueContract = MockValueContract(
        id = "testValue".id,
        value = Mock(
            data = "testValue",
        ),
    )

    override val string: String
        get() = """
            {
                "id": "testValue",
                "value": {
                    "data": "testValue"
                }
            }
        """.trimIndent()
}
