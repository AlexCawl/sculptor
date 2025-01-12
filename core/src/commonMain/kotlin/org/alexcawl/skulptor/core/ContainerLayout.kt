package org.alexcawl.skulptor.core

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
abstract class ContainerLayout : BaseLayout {
    abstract fun ContainerLayoutScope.build(): @Composable () -> Unit

    internal fun internalBuild(scope: ContainerLayoutScope): @Composable () -> Unit =
        with(scope) { build() }

    interface ContainerLayoutScope : BaseLayout.BaseLayoutScope {
        fun <T : BaseLayout> getLayout(id: String): T

        fun <T : BaseLayout> getLayoutOrNull(id: String): T?

        @Composable
        fun Any.place(layout: BaseLayout)
    }
}
