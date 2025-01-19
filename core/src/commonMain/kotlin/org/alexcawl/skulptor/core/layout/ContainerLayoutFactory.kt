package org.alexcawl.skulptor.core.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.factory.BaseLayout
import org.alexcawl.skulptor.core.getLayout
import org.alexcawl.skulptor.core.getState
import org.alexcawl.skulptor.core.schemaProvider
import org.alexcawl.skulptor.core.scopeProvider

abstract class ContainerLayoutFactory<S : BaseState> : BaseLayoutFactory<S>() {
    @Composable
    protected fun place(layoutId: String, scope: Any = Any()) {
        val schema = schemaProvider.current
        CompositionLocalProvider(value = scopeProvider provides scope) {
            val layout: BaseLayout<out BaseState> = schema.getLayout(layoutId)
            val screen = layout.factory.invoke(
                layoutId = layoutId,
                stateProvider = { schema.getState(layout.id) },
                modifiers = layout.modifiers
            )
            screen.invoke()
        }
    }
}
