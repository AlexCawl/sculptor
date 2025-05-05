package org.alexcawl.sculptor.internal.di.compose

import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.internal.di.DiTree
import kotlin.properties.ReadOnlyProperty

public fun diTree(
    viewModelKey: String,
    factory: () -> DiTree,
): ReadOnlyProperty<ViewModelStoreOwner,DiTree> {
    return DiTreeLazyDelegate(
        viewModelKey = viewModelKey,
        factory = factory,
    )
}
