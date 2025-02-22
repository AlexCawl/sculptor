package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.builder.placer.LayoutPlacer
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.contract.id

public sealed interface StateBuilder<SC : StateContract> : Builder<SC>, LayoutPlacer {
    public override fun place(layout: LayoutContract)

    public fun <LC : LayoutContract> layout(block: () -> LC): Unit = place(block())

    public override fun build(): SC

    public companion object Factory {
        public operator fun <SC : StateContract> invoke(
            scope: LayoutPlacer,
            identifier: String,
            modifiers: List<ModifierContract> = emptyList(),
            builderBlock: StateBuilder<SC>.(Identifier, List<ModifierContract>) -> SC,
        ): StateBuilder<SC> = StateBuilderImpl(
            scope = scope,
            identifier = identifier.id,
            modifiers = modifiers,
            builderBlock = builderBlock,
        )
    }
}

private class StateBuilderImpl<SC : StateContract>(
    scope: LayoutPlacer,
    private val identifier: Identifier,
    private val modifiers: List<ModifierContract> = emptyList(),
    private val builderBlock: StateBuilder<SC>.(Identifier, List<ModifierContract>) -> SC,
) : StateBuilder<SC>, LayoutPlacer by scope {
    override fun build(): SC = this.builderBlock(identifier, modifiers)
}
