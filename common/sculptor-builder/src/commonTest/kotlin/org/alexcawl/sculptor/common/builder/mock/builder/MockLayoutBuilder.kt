package org.alexcawl.sculptor.common.builder.mock.builder

import org.alexcawl.sculptor.common.builder.LayoutBuilder
import org.alexcawl.sculptor.common.builder.ScaffoldBuilder
import org.alexcawl.sculptor.common.builder.mock.MockLayoutContract
import org.alexcawl.sculptor.common.builder.mock.MockStateContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.id

class MockLayoutBuilder(
    identifier: String,
    defaultModifiers: List<ModifierContract>,
) : LayoutBuilder<MockLayoutContract, MockStateContract>(
    identifier = identifier.id,
    defaultModifiers = defaultModifiers,
) {
    override fun build(): MockLayoutContract = MockLayoutContract(
        id = identifier,
        state = states.first().id,
        modifiers = modifiers,
        states = states,
    )
}

inline fun ScaffoldBuilder.mockLayout(
    id: String,
    modifier: List<ModifierContract> = emptyList(),
    builder: MockLayoutBuilder.() -> Unit,
) {
    val layoutBuilder = MockLayoutBuilder(
        identifier = id,
        defaultModifiers = modifier
    )
    layoutBuilder.builder()
    addLayout(layoutBuilder.build())
}
