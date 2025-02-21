package org.alexcawl.sculptor.common.builder.mock.builder

import org.alexcawl.sculptor.common.builder.StateBuilder
import org.alexcawl.sculptor.common.builder.mock.MockStateContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.id

class MockStateBuilder(
    identifier: String,
    defaultModifiers: List<ModifierContract>,
) : StateBuilder<MockStateContract>(
    identifier = identifier.id,
    defaultModifiers = defaultModifiers,
){
    var testValue: String? = null

    override fun build(): MockStateContract = MockStateContract(
        id = identifier,
        modifiers = modifiers,
        testValue = testValue ?: error("testValue is not specified"),
    )
}

inline fun MockLayoutBuilder.mockState(
    id: String,
    modifier: List<ModifierContract> = emptyList(),
    builder: MockStateBuilder.() -> Unit,
) {
    val stateBuilder = MockStateBuilder(
        identifier = id,
        defaultModifiers = modifier,
    )
    stateBuilder.builder()
    addState(stateBuilder.build())
}
