package org.alexcawl.sculptor.runtime.engine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState
import org.alexcawl.sculptor.runtime.engine.presentation.CompositionLocalDiTree
import org.alexcawl.sculptor.runtime.engine.presentation.CompositionLocalStore
import org.alexcawl.sculptor.runtime.engine.presentation.Content

@Stable
public interface SculptorScreen {
    @Composable
    public fun open(
        intent: SculptorIntent,
        loadingScreen: @Composable (modifier: Modifier) -> Unit,
        errorScreen: @Composable (modifier: Modifier) -> Unit,
        modifier: Modifier,
    )

    @Composable
    public fun provides(content: @Composable () -> Unit)
}

@Composable
public fun SculptorScreen(
    intent: SculptorIntent,
    loadingScreen: @Composable (modifier: Modifier) -> Unit,
    errorScreen: @Composable (modifier: Modifier) -> Unit,
    modifier: Modifier,
) {
    val diTree: DiTree = CompositionLocalDiTree.current
    val store: Store<SculptorState, SculptorEvent> = CompositionLocalStore.current
    Content(
        intent = intent,
        diTree = { diTree },
        store = { store },
        loadingScreen = loadingScreen,
        errorScreen = errorScreen,
        modifier = modifier,
    )
}
