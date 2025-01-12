package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
abstract class ComponentLayout : BaseLayout {
    abstract fun ComponentLayoutScope.build(): @Composable () -> Unit

    internal fun internalBuild(scope: ComponentLayoutScope): @Composable () -> Unit =
        with(scope) { build() }

    interface ComponentLayoutScope : BaseLayout.BaseLayoutScope
}
