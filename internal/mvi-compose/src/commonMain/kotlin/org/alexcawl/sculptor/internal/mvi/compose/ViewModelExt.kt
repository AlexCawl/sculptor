package org.alexcawl.sculptor.internal.mvi.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.reflect.KClass

@PublishedApi
internal inline fun <reified T : ViewModel> ViewModelStore.get(
    key: String,
    noinline factory: (creationExtras: CreationExtras) -> T,
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
