package org.alexcawl.sculptor.internal.mvi.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.plus
import org.alexcawl.sculptor.internal.mvi.core.Store
import kotlin.coroutines.CoroutineContext

@PublishedApi
internal class StoreViewModel<out State : Any, in Event : Any>(
    private val store: Store<State, Event>,
    coroutineContext: CoroutineContext,
) : ViewModel(), Store<State, Event> by store {
    init {
        store.launchIn(coroutineScope = viewModelScope + coroutineContext)
    }
}
