package org.alexcawl.sculptor.internal.mvi.lifecycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Dispatchers
import org.alexcawl.sculptor.internal.mvi.core.Store
import kotlin.coroutines.CoroutineContext
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

public fun <T : Store<*, *, *>> store(
    coroutineContext: CoroutineContext = Dispatchers.Default,
    sharedViewModelKey: String? = null,
    factory: () -> T
): ReadOnlyProperty<ViewModelStoreOwner, T> {
    return StoreLazyDelegate(
        viewModelKey = sharedViewModelKey,
        coroutineContext = coroutineContext,
        factory = factory,
    )
}

internal class StoreLazyDelegate<T : Store<*, *, *>>(
    private val viewModelKey: String? = null,
    private val factory: () -> T,
    private val coroutineContext: CoroutineContext,
) : ReadOnlyProperty<ViewModelStoreOwner, T> {
    private var value: T? = null

    override fun getValue(thisRef: ViewModelStoreOwner, property: KProperty<*>): T {
        return value ?: run {
            val key: String = viewModelKey
                ?: keyFromProperty(thisRef = thisRef, property = property)
            val viewModelStore: ViewModelStore = thisRef.viewModelStore
            val storeViewModel: StoreViewModel<T> = viewModelStore.get(key) {
                StoreViewModel(
                    store = factory(),
                    coroutineContext = coroutineContext,
                )
            }
            storeViewModel.store.also { value = it }
        }
    }

    private fun keyFromProperty(thisRef: ViewModelStoreOwner, property: KProperty<*>): String {
        return "${thisRef::class}#${property.name}"
    }
}

private inline fun <reified T : ViewModel> ViewModelStore.get(
    key: String,
    crossinline factory: (creationExtras: CreationExtras) -> T,
): T {
    val viewModelFactory = object : ViewModelProvider.Factory {
        override fun <VM : ViewModel> create(modelClass: KClass<VM>, extras: CreationExtras): VM {
            @Suppress("UNCHECKED_CAST")
            return factory(extras) as VM
        }
    }
    return ViewModelProvider
        .create(store = this, factory = viewModelFactory)
        .get(key = key, modelClass = T::class)
}
