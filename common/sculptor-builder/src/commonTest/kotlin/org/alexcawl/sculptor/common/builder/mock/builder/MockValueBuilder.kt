package org.alexcawl.sculptor.common.builder.mock.builder

import org.alexcawl.sculptor.common.builder.ScaffoldBuilder
import org.alexcawl.sculptor.common.builder.ValueBuilder
import org.alexcawl.sculptor.common.builder.mock.Mock
import org.alexcawl.sculptor.common.builder.mock.MockValueContract
import org.alexcawl.sculptor.common.contract.id

class MockValueBuilder(
    identifier: String,
    private val value: Mock,
) : ValueBuilder<MockValueContract>(identifier = identifier.id) {
    override fun build(): MockValueContract = MockValueContract(
        id = identifier,
        value = value,
    )
}

fun ScaffoldBuilder.mockValue(
    id: String,
    value: Mock,
) {
    val valueBuilder = MockValueBuilder(
        identifier = id,
        value = value,
    )
    addValue(valueBuilder.build())
}
