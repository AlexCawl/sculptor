package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.Section.Block

public fun interface ComponentProvider {
    public suspend fun findBlock(identifier: Identifier): Block
}
