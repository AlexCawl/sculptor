package org.alexcawl.sculptor.runtime.engine.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.runtime.contractor.renderer.RootRenderer
import org.alexcawl.sculptor.runtime.engine.SculptorIntent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState

@Composable
internal fun SculptorUiImpl(
    intent: SculptorIntent,
    diTree: () -> DiTree,
    store: () -> Store<SculptorState, SculptorEvent>,
    loadingScreen: @Composable () -> Unit,
    errorScreen: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state: SculptorState by store().state.collectAsState(initial = SculptorState.Loading)
    AnimatedContent(
        targetState = state,
        label = SCULPTOR_ANIMATION,
        modifier = modifier,
    ) { targetState: SculptorState ->
        when (targetState) {
            is SculptorState.Loading -> loadingScreen()
            is SculptorState.Idle -> {
                val rootRenderer: RootRenderer = remember(
                    key1 = targetState,
                    calculation = diTree()::get,
                )
                rootRenderer.Draw(layout = targetState.layout)
            }

            is SculptorState.Error -> errorScreen()
        }
    }
    LaunchedEffect(key1 = intent) {
        store().dispatch(
            event = SculptorEvent.HandleIntentEvent(intent = intent),
        )
    }
}

private const val SCULPTOR_ANIMATION: String = "SCULPTOR_ANIMATION"
