package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract

public abstract class LayoutBuilder<LC : LayoutContract, SC : StateContract>(
    protected val identifier: Identifier,
    defaultModifiers: List<ModifierContract>,
) : Builder<LC> {
    protected val modifiers: MutableList<ModifierContract> = defaultModifiers.toMutableList()
    protected val states: MutableList<SC> = mutableListOf()

    public fun addModifier(modifier: ModifierContract) {
        modifiers.add(modifier)
    }

    public fun addState(state: SC) {
        states.add(state)
    }

    abstract override fun build(): LC
}
