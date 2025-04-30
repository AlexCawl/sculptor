package org.alexcawl.sculptor.internal.mvi.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.alexcawl.sculptor.internal.mvi.core.Store

public fun <State : Any, News : Any> Store<State, *, News>.collectWithLifecycle(
    lifecycleOwner: LifecycleOwner,
    stateCollector: (State) -> Unit,
    newsCollector: (News) -> Unit,
): Unit = collectBuild(
    lifecycleOwner = lifecycleOwner,
    state = state,
    stateCollector = stateCollector,
    news = news,
    newsCollector = newsCollector,
)

private fun <State : Any, News : Any> collectBuild(
    lifecycleOwner: LifecycleOwner,
    state: Flow<State>,
    stateCollector: (State) -> Unit,
    news: Flow<News>,
    newsCollector: (News) -> Unit,
) {
    val lifecycle: Lifecycle = lifecycleOwner.lifecycle

    check(value = (lifecycle.currentState == Lifecycle.State.INITIALIZED)) {
        "Must be called when lifecycle in initialized state"
    }

    with(lifecycleOwner.lifecycleScope) {
        launch {
            state.flowWithLifecycle(
                lifecycle = lifecycle,
                minActiveState = Lifecycle.State.STARTED,
            ).collect(collector = stateCollector::invoke)
        }

        launch {
            news.flowWithLifecycle(
                lifecycle = lifecycle,
                minActiveState = Lifecycle.State.RESUMED,
            ).collect(collector = newsCollector::invoke)
        }
    }
}
