package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract

public abstract class StateBuilder<SC : StateContract>(
    protected val identifier: Identifier,
    defaultModifiers: List<ModifierContract>,
) : Builder<SC> {
    protected val modifiers: MutableList<ModifierContract> = defaultModifiers.toMutableList()

    public fun addModifier(modifier: ModifierContract) {
        modifiers.add(modifier)
    }

    abstract override fun build(): SC
}
