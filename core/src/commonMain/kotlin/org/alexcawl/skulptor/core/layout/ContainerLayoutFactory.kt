package org.alexcawl.skulptor.core.layout

import androidx.compose.runtime.Composable
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.Skulptor.getLayout
import org.alexcawl.skulptor.core.Skulptor.getState
import org.alexcawl.skulptor.core.Skulptor.withScope

abstract class ContainerLayoutFactory<S : BaseState> : BaseLayoutFactory<S>() {
    @Composable
    protected fun place(layoutId: String, scope: Any = Any()) {
        withScope(scope) {
            val layout = getLayout(layoutId)
            layout.factory.build(
                layoutId = layoutId,
                stateProvider = { getState(layout.id) },
                modifiers = layout.modifiers
            )
        }
    }
}
