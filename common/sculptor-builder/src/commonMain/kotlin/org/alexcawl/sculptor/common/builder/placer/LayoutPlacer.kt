package org.alexcawl.sculptor.common.builder.placer

import org.alexcawl.sculptor.common.builder.LayoutBuilder
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract

public interface LayoutPlacer {
    public fun place(layout: LayoutContract)

    public fun <LC : LayoutContract, SC : StateContract> layout(
        identifier: String,
        modifiers: List<ModifierContract> = emptyList(),
        layoutBuilderBlock: LayoutBuilder<LC, SC>.(Identifier, List<ModifierContract>, List<SC>) -> LC,
    ): Unit = LayoutBuilder.invoke(
        scope = this,
        identifier = identifier,
        modifiers = modifiers,
        layoutBuilderBlock = layoutBuilderBlock,
    ).build().let(::place)
}
