package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.common.di.DiTree
import org.alexcawl.sculptor.engine.di.api.SculptorBuilder
import org.alexcawl.sculptor.engine.di.api.SculptorGlobalBuilder
import org.alexcawl.sculptor.engine.di.impl.SculptorGlobalBuilderImpl
import kotlin.properties.ReadOnlyProperty

public interface Sculptor {
    @Composable
    public fun open(deeplink: String)

    public companion object : SculptorInitializer {
        private var globalDiTreeInstance: Lazy<DiTree> = lazy {
            error("Global DI tree is not initialized")
        }

        override fun initialize(builder: SculptorGlobalBuilder.() -> Unit) {
            globalDiTreeInstance = lazy { SculptorGlobalBuilderImpl().apply(builder).build() }
        }

        override fun create(builder: SculptorBuilder.() -> Unit): ReadOnlyProperty<ViewModelStoreOwner, Sculptor> {
            TODO("Not yet implemented")
        }
    }
}
