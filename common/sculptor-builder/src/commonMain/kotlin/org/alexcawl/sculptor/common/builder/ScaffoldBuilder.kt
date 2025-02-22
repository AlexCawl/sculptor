package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.builder.placer.LayoutPlacer
import org.alexcawl.sculptor.common.builder.placer.ValuePlacer
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.contract.ValueContract
import org.alexcawl.sculptor.common.contract.id

public interface ScaffoldBuilder : Builder<Scaffold>, ValuePlacer, LayoutPlacer {
    public override fun place(value: ValueContract)

    public fun <VC : ValueContract> value(
        identifier: String,
        builderBlock: ValueBuilder<VC>.(Identifier) -> VC,
    ): Unit = ValueBuilder.invoke(
        identifier = identifier,
        builderBlock = builderBlock,
    ).build().let(::place)

    public override fun place(layout: LayoutContract)

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

    public override fun build(): Scaffold

    public companion object Factory {
        public operator fun invoke(
            forcedRoot: String? = null,
        ): ScaffoldBuilder = ScaffoldBuilderImpl(
            forcedRoot = forcedRoot?.id,
        )
    }
}

private class ScaffoldBuilderImpl(
    private val forcedRoot: Identifier? = null,
) : ScaffoldBuilder {
    private val values: MutableList<ValueContract> = mutableListOf()
    private val layouts: MutableList<LayoutContract> = mutableListOf()

    override fun place(value: ValueContract) {
        values.add(value)
    }

    override fun place(layout: LayoutContract) {
        layouts.add(layout)
    }

    override fun build(): Scaffold {
        return Scaffold(
            rootLayoutId = forcedRoot ?: layouts.first().id,
            values = values.toList(),
            layouts = layouts.toList()
        )
    }
}
