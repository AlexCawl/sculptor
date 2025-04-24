package org.alexcawl.sculptor.engine

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.engine.di.api.SculptorBuilder
import org.alexcawl.sculptor.engine.di.api.SculptorGlobalBuilder
import kotlin.properties.ReadOnlyProperty

public interface SculptorInitializer {
    public fun initialize(builder: SculptorGlobalBuilder.() -> Unit)

    public fun create(builder: SculptorBuilder.() -> Unit): ReadOnlyProperty<ViewModelStoreOwner, Sculptor>
}