package org.alexcawl.sculptor.runtime.engine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.runtime.engine.presentation.SculptorDelegateImpl
import org.alexcawl.sculptor.runtime.engine.presentation.SculptorGlobalBuilderImpl
import kotlin.properties.ReadOnlyProperty

@Stable
public interface Sculptor {
    @Composable
    public fun open(
        intent: SculptorIntent,
        loadingScreen: @Composable (modifier: Modifier) -> Unit,
        errorScreen: @Composable (modifier: Modifier) -> Unit,
        modifier: Modifier,
    )

    @Composable
    public fun provides(content: @Composable () -> Unit)

    public companion object {
        private var globalDiTreeInstance: DiTree? = null

        internal val globalDiTree: DiTree
           get() = globalDiTreeInstance?: error("Sculptor is not initialized")

        @JvmStatic
        public val isInitialized: Boolean
            get() = globalDiTreeInstance != null

        @JvmStatic
        public fun initialize(builder: SculptorGlobalBuilder.() -> Unit) {
            val diTree: DiTree = SculptorGlobalBuilderImpl().apply(builder).build()
            return when (globalDiTreeInstance) {
                null -> globalDiTreeInstance = diTree
                else -> error("Sculptor is already initialized")
            }
        }

        @JvmStatic
        public fun create(builder: SculptorBuilder.() -> Unit): ReadOnlyProperty<ViewModelStoreOwner, Sculptor> {
            return SculptorDelegateImpl(
                globalDiTree = globalDiTreeInstance ?: error("Sculptor is not initialized"),
                builder = builder,
            )
        }
    }
}
