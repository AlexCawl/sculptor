package org.alexcawl.sculptor.internal.mvi.compose

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.internal.mvi.core.Store
import kotlin.coroutines.CoroutineContext
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class StoreLazyDelegate<out State : Any, in Event : Any>(
    private val viewModelKey: String,
    private val factory: () -> Store<State, Event>,
    private val coroutineContext: CoroutineContext,
) : ReadOnlyProperty<ViewModelStoreOwner, Store<State, Event>> {
    private var value: Store<State, Event>? = null

    override fun getValue(thisRef: ViewModelStoreOwner, property: KProperty<*>): Store<State, Event> {
        return value ?: run {
            val viewModelStore: ViewModelStore = thisRef.viewModelStore
            val storeViewModel: Store<State, Event> = viewModelStore.get(key = viewModelKey) {
                StoreViewModel(
                    store = factory(),
                    coroutineContext = coroutineContext,
                )
            }
            storeViewModel.also { eventStore: Store<State, Event> ->
                value = eventStore
            }
        }
    }
}
