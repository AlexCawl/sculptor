package org.alexcawl.sculptor.runtime.engine.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import kotlinx.coroutines.CoroutineScope
import org.alexcawl.sculptor.core.renderer.RendererScope
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.DiTreeBuilder
import org.alexcawl.sculptor.internal.di.compose.diTree
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.internal.mvi.compose.store
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorStore

internal class SculptorImpl(
    viewModelStoreOwner: ViewModelStoreOwner,
    diTreeBuilder: DiTreeBuilder,
) : Sculptor, ViewModelStoreOwner by viewModelStoreOwner {
    private val diTree: DiTree by diTree(
        viewModelKey = "SculptorDiTree",
        diTreeBuilder = diTreeBuilder,
    )
    private val store: Store<SculptorState, SculptorEvent> by store(
        viewModelKey = "SculptorStore",
        factory = { diTree.get(SculptorStore::class) },
    )
    private val rendererScope: RendererScope by lazy(initializer = diTree::get)

    @Composable
    override fun open(
        intent: SculptorIntent?,
        placeholderScreen: @Composable (modifier: Modifier) -> Unit,
        loadingScreen: @Composable (modifier: Modifier) -> Unit,
        errorScreen: @Composable (modifier: Modifier) -> Unit,
        modifier: Modifier,
    ) {
        val scope: CoroutineScope = rememberCoroutineScope()
        store.launchIn(coroutineScope = scope)

        val sculptorState: SculptorState by store.state.collectAsState(initial = SculptorState.Initial)
        AnimatedContent(
            targetState = sculptorState,
        ) { targetState: SculptorState ->
            rendererScope.render(
                state = targetState,
                placeholderScreen = placeholderScreen,
                loadingScreen = loadingScreen,
                errorScreen = errorScreen,
                modifier = modifier,
            )
        }

        LaunchedEffect(key1 = intent) {
            if (intent != null) {
                store.dispatch(
                    event = SculptorEvent.HandleIntentEvent(intent = intent),
                )
            }
        }
    }

    @Composable
    private fun RendererScope.render(
        state: SculptorState,
        placeholderScreen: @Composable (modifier: Modifier) -> Unit,
        loadingScreen: @Composable (modifier: Modifier) -> Unit,
        errorScreen: @Composable (modifier: Modifier) -> Unit,
        modifier: Modifier,
    ) {
        when (state) {
            is SculptorState.Initial -> placeholderScreen(modifier)
            is SculptorState.Loading -> loadingScreen(modifier)
            is SculptorState.Idle -> draw(layout = state.layout)
            is SculptorState.Error -> errorScreen(modifier)
        }
    }
}
