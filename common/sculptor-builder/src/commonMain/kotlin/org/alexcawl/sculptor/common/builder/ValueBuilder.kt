package org.alexcawl.sculptor.common.builder

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ValueContract
import org.alexcawl.sculptor.common.contract.id

public interface ValueBuilder<VC : ValueContract> : Builder<VC> {
    public override fun build(): VC

    public companion object Factory {
        public operator fun <VC : ValueContract> invoke(
            identifier: String,
            builderBlock: ValueBuilder<VC>.(Identifier) -> VC,
        ): ValueBuilder<VC> = ValueBuilderImpl(
            identifier = identifier.id,
            builderBlock = builderBlock,
        )
    }
}

private class ValueBuilderImpl<VC : ValueContract>(
    private val identifier: Identifier,
    private val builderBlock: ValueBuilder<VC>.(Identifier) -> VC,
) : ValueBuilder<VC> {
    override fun build(): VC = this.builderBlock(identifier)
}
