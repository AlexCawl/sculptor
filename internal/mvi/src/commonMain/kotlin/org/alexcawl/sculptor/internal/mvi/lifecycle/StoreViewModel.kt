package org.alexcawl.sculptor.internal.mvi.lifecycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.plus
import org.alexcawl.sculptor.internal.mvi.core.Store
import kotlin.coroutines.CoroutineContext

internal class StoreViewModel<T : Store<*, *>>(
    val store: T,
    coroutineContext: CoroutineContext,
) : ViewModel() {
    init {
        store.launchIn(coroutineScope = viewModelScope + coroutineContext)
    }
}
