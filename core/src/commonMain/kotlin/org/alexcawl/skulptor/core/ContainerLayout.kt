package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
abstract class ContainerLayout : BaseLayout {
    abstract fun Scope.build(): @Composable () -> Unit

    internal fun internalBuild(scope: Scope): @Composable () -> Unit =
        with(scope) { build() }

    interface Scope : BaseLayout.BaseLayoutScope {
        fun <T : BaseLayout> getLayout(id: String): T?

        @Composable
        fun Any.place(layout: BaseLayout)
    }
}
