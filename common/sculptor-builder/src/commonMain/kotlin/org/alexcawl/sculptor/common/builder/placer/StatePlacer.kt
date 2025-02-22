package org.alexcawl.sculptor.common.builder.placer

import org.alexcawl.sculptor.common.builder.StateBuilder
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract

public interface StatePlacer {
    public fun place(state: StateContract)

    public fun <SC : StateContract> state(
        identifier: String,
        modifiers: List<ModifierContract> = emptyList(),
        builderBlock: StateBuilder<SC>.(Identifier, List<ModifierContract>) -> SC,
    )
}
