package org.alexcawl.sculptor.runtime.engine.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.runtime.engine.SculptorIntent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState
import org.alexcawl.sculptor.runtime.renderer.RootRenderer

@Composable
internal fun Content(
    intent: SculptorIntent,
    diTree: () -> DiTree,
    store: () -> Store<SculptorState, SculptorEvent>,
    loadingScreen: @Composable (modifier: Modifier) -> Unit,
    errorScreen: @Composable (modifier: Modifier) -> Unit,
    modifier: Modifier,
) {
    val state: SculptorState by store().state.collectAsState(initial = SculptorState.Loading)
    AnimatedContent(
        targetState = state,
        label = SCULPTOR_ANIMATION,
    ) { targetState: SculptorState ->
        when (targetState) {
            is SculptorState.Loading -> loadingScreen(modifier)
            is SculptorState.Idle -> {
                val rootRenderer: RootRenderer = remember(
                    key1 = targetState,
                    calculation = diTree()::get,
                )
                rootRenderer.Draw(
                    modifier = modifier,
                    layout = targetState.layout,
                )
            }

            is SculptorState.Error -> errorScreen(modifier)
        }
    }
    LaunchedEffect(key1 = intent) {
        store().dispatch(
            event = SculptorEvent.HandleIntentEvent(intent = intent),
        )
    }
}

@Composable
internal inline fun Scope(
    diTree: () -> DiTree,
    store: () -> Store<SculptorState, SculptorEvent>,
    noinline content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        CompositionLocalDiTree provides diTree(),
        CompositionLocalStore provides store(),
        content = content,
    )
}

internal val CompositionLocalDiTree: ProvidableCompositionLocal<DiTree> =
    staticCompositionLocalOf { error("No DiTree provided") }

internal val CompositionLocalStore: ProvidableCompositionLocal<Store<SculptorState, SculptorEvent>> =
    staticCompositionLocalOf { error("No Store provided") }

private const val SCULPTOR_ANIMATION: String = "SCULPTOR_ANIMATION"
