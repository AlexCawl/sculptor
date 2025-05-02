package org.alexcawl.sculptor.runtime.engine.di

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import org.alexcawl.sculptor.core.renderer.RendererScope
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.internal.mvi.compose.rememberStore
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.mvi.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.mvi.SculptorState
import org.alexcawl.sculptor.runtime.engine.mvi.SculptorStore

internal class SculptorImpl(diTree: DiTree) : Sculptor {
    private val store: SculptorStore by lazy<SculptorStore>(diTree::get)
    private val rendererScope: RendererScope by lazy<RendererScope>(diTree::get)

    @Composable
    override fun open(
        deeplink: String,
        loadingScreen: @Composable (modifier: Modifier) -> Unit,
        errorScreen: @Composable (modifier: Modifier) -> Unit,
        modifier: Modifier,
    ) {
        val scope: CoroutineScope = rememberCoroutineScope()
        val store: Store<SculptorState, SculptorEvent> = rememberStore(
            viewModelKey = TAG,
            factory = { store },
        )
        store.launchIn(coroutineScope = scope)

        val sculptorState: SculptorState by store.state.collectAsState(initial = SculptorState.Loading)
        AnimatedContent(
            targetState = sculptorState,
        ) { targetState: SculptorState ->
            render(
                state = targetState,
                loadingScreen = loadingScreen,
                errorScreen = errorScreen,
                modifier = modifier,
            )
        }

        LaunchedEffect(deeplink) {
            store.dispatch(
                event = SculptorEvent.LoadInitialContentEvent(deeplink = deeplink),
            )
        }
    }

    @Composable
    private fun render(
        state: SculptorState,
        loadingScreen: @Composable (modifier: Modifier) -> Unit,
        errorScreen: @Composable (modifier: Modifier) -> Unit,
        modifier: Modifier,
    ) {
        when (state) {
            is SculptorState.Content -> rendererScope.draw(layout = state.layout)
            is SculptorState.Error -> errorScreen(modifier)
            is SculptorState.Loading -> loadingScreen(modifier)
            else -> error(message = "Unknown state: $state")
        }
    }

    private companion object {
        private const val TAG = "SculptorImpl"
    }
}
