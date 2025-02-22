package org.alexcawl.sculptor.common.builder.placer

import org.alexcawl.sculptor.common.builder.ValueBuilder
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ValueContract

public interface ValuePlacer<VC : ValueContract> {
    public fun place(value: VC)

    public fun value(
        identifier: String,
        builderBlock: ValueBuilder<VC>.(Identifier) -> VC,
    ): Unit = ValueBuilder.invoke(
        identifier = identifier,
        builderBlock = builderBlock,
    ).build().let(::place)
}
