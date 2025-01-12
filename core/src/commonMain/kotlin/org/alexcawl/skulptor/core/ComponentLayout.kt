package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
abstract class ComponentLayout : BaseLayout {
    abstract fun Scope.build(): @Composable () -> Unit

    internal fun internalBuild(scope: Scope): @Composable () -> Unit =
        with(scope) { build() }

    interface Scope : BaseLayout.BaseLayoutScope
}
