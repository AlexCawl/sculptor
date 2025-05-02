package org.alexcawl.sculptor.runtime.engine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.runtime.engine.di.SculptorDelegate
import org.alexcawl.sculptor.runtime.engine.di.SculptorGlobalBuilderImpl
import kotlin.properties.ReadOnlyProperty

@Stable
public interface Sculptor {
    @Composable
    public fun open(
        deeplink: String,
        loadingScreen: @Composable (modifier: Modifier) -> Unit,
        errorScreen: @Composable (modifier: Modifier) -> Unit,
        modifier: Modifier = Modifier,
    )

    public companion object {
        private var globalDiTreeInstance: DiTree? = null

        public fun isInitialized(): Boolean = globalDiTreeInstance != null

        public fun initialize(builder: SculptorGlobalBuilder.() -> Unit) {
            val diTree: DiTree = SculptorGlobalBuilderImpl().apply(builder).build()
            return when (globalDiTreeInstance) {
                null -> globalDiTreeInstance = diTree
                else -> error("Sculptor is already initialized")
            }
        }

        public fun create(builder: SculptorBuilder.() -> Unit): ReadOnlyProperty<ViewModelStoreOwner, Sculptor> {
            return SculptorDelegate(
                globalDiTree = globalDiTreeInstance ?: error("Sculptor is not initialized"),
                builder = builder,
            )
        }
    }
}
