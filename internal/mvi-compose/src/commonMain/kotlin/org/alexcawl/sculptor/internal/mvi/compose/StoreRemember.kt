package org.alexcawl.sculptor.internal.mvi.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import org.alexcawl.sculptor.internal.mvi.core.Store
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Stable
@Composable
public inline fun <State : Any, Event : Any> rememberStore(
    viewModelKey: String,
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    crossinline factory: @DisallowComposableCalls () -> Store<State, Event>,
): Store<State, Event> {
    val viewModelStoreOwner: ViewModelStoreOwner = LocalViewModelStoreOwner.current
        ?: error("No ViewModelStoreOwner in compose hierarchy")
    return remember(key1 = viewModelKey) {
        viewModelStoreOwner.viewModelStore.get(key = viewModelKey) {
            StoreViewModel(
                store = factory(),
                coroutineContext = coroutineContext,
            )
        }
    }
}
