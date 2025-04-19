package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.Block
import org.alexcawl.sculptor.common.contract.Identifier

public fun interface ComponentProvider {
    public suspend operator fun invoke(id: Identifier): Block

    public companion object {
        public operator fun invoke(blocks: List<Block>): ComponentProvider =
            ComponentProvider { identifier: Identifier ->
                blocks.find { it.id == identifier }
                    ?: error(message = "Cannot resolve section for id $identifier")
            }
    }
}
