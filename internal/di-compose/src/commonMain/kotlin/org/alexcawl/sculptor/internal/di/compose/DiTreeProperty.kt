package org.alexcawl.sculptor.internal.di.compose

import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.DiTreeBuilder
import kotlin.properties.ReadOnlyProperty

public fun diTree(
    viewModelKey: String,
    diTreeBuilder: DiTreeBuilder,
): ReadOnlyProperty<ViewModelStoreOwner,DiTree> {
    return DiTreeLazyDelegate(
        viewModelKey = viewModelKey,
        diTreeBuilder = diTreeBuilder,
    )
}
