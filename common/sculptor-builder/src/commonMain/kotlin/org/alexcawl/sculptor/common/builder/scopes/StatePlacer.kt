package org.alexcawl.sculptor.common.builder.scopes

import org.alexcawl.sculptor.common.builder.SculptorDsl
import org.alexcawl.sculptor.common.contract.StateContract

@SculptorDsl
public interface StatePlacer {
    public fun state(state: StateContract)

    public fun <SC : StateContract> state(builder: () -> SC)
}
