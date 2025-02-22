package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.builder.scopes.StatePlacer
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.StateContract

@SculptorDsl
public interface SectionBuilder : StatePlacer, Builder<Section> {
    public companion object Factory {
        public fun create(
            identifier: Identifier,
            modifiers: List<ModifierContract> = emptyList(),
            forcedState: Identifier? = null,
            builder: StatePlacer.() -> Unit
        ): Section = SectionBuilderImpl(
            identifier = identifier,
            modifiers = modifiers,
            forcedState = forcedState,
        ).apply(builder).build()
    }
}

private class SectionBuilderImpl(
    private val identifier: Identifier,
    private val modifiers: List<ModifierContract> = emptyList(),
    private val forcedState: Identifier? = null,
) : SectionBuilder {
    private val _states: MutableList<StateContract> = mutableListOf()

    override fun state(state: StateContract) {
        _states.add(state)
    }

    override fun <SC : StateContract> state(builder: () -> SC) = state(builder())

    override fun build(): Section = Section(
        id = identifier,
        modifiers = modifiers,
        forcedState = forcedState,
        states = _states,
    )
}
