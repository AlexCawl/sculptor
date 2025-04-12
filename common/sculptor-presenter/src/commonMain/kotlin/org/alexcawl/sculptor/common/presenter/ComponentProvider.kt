package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.Component
import org.alexcawl.sculptor.common.contract.Identifier

public fun interface ComponentProvider {
    public suspend operator fun invoke(id: Identifier): Component

    public companion object {
        public operator fun invoke(components: List<Component>): ComponentProvider =
            ComponentProvider { identifier: Identifier ->
                components.find { it.id == identifier }
                    ?: error(message = "Cannot resolve section for id $identifier")
            }
    }
}
