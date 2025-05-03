package org.alexcawl.sculptor.internal.di.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.DiTreeBuilder

@Stable
@Composable
public inline fun rememberDiTree(
    viewModelKey: String,
    crossinline builder: @DisallowComposableCalls () -> DiTreeBuilder,
): DiTree {
    val viewModelStoreOwner: ViewModelStoreOwner = LocalViewModelStoreOwner.current
        ?: error("No ViewModelStoreOwner in compose hierarchy")
    return remember(key1 = viewModelKey) {
        viewModelStoreOwner.viewModelStore.get(key = viewModelKey) {
            DiTreeViewModel(diTree = builder().build())
        }
    }
}
