package org.alexcawl.sculptor.internal.mvi.compose

import androidx.lifecycle.ViewModelStoreOwner
import kotlinx.coroutines.Dispatchers
import org.alexcawl.sculptor.internal.mvi.core.Store
import kotlin.coroutines.CoroutineContext
import kotlin.properties.ReadOnlyProperty

public fun <State : Any, Event : Any> store(
    viewModelKey: String,
    coroutineContext: CoroutineContext = Dispatchers.Default,
    factory: () -> Store<State, Event>,
): ReadOnlyProperty<ViewModelStoreOwner, Store<State, Event>> {
    return StoreLazyDelegate(
        viewModelKey = viewModelKey,
        coroutineContext = coroutineContext,
        factory = factory,
    )
}
