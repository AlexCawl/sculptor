package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner
import kotlin.properties.ReadOnlyProperty

public interface Sculptor {
    @Composable
    public fun open(deeplink: String)

    public companion object {
        public fun initialize(builder: SculptorGlobalBuilder.() -> Unit): Nothing = TODO()

        public fun create(builder: SculptorBuilder.() -> Unit): ReadOnlyProperty<ViewModelStoreOwner, Sculptor> = TODO()
    }
}
