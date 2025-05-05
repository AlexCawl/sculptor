package org.alexcawl.sculptor.runtime.engine

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.DiTreeBuilder
import org.alexcawl.sculptor.internal.di.compose.rememberDiTree
import org.alexcawl.sculptor.internal.mvi.compose.rememberStore
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.runtime.engine.Sculptor.Companion.globalDiTree
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState
import org.alexcawl.sculptor.runtime.engine.domain.SculptorStore
import org.alexcawl.sculptor.runtime.engine.presentation.SculptorBuilderImpl
import org.alexcawl.sculptor.runtime.engine.presentation.SculptorScreenImpl
import org.alexcawl.sculptor.runtime.engine.presentation.SculptorScreenScope

@Composable
public fun SculptorScreen(
    intent: SculptorIntent,
    loadingScreen: @Composable (modifier: Modifier) -> Unit,
    errorScreen: @Composable (modifier: Modifier) -> Unit,
    modifier: Modifier,
) {
    val diTree: DiTree = SculptorScreenScope.diTree
    val store: Store<SculptorState, SculptorEvent> = SculptorScreenScope.store
    SculptorScreenImpl(
        intent = intent,
        diTree = { diTree },
        store = { store },
        loadingScreen = loadingScreen,
        errorScreen = errorScreen,
        modifier = modifier,
    )
}

@Composable
public fun SculptorScreen(
    intent: SculptorIntent,
    loadingScreen: @Composable (modifier: Modifier) -> Unit,
    errorScreen: @Composable (modifier: Modifier) -> Unit,
    modifier: Modifier = Modifier,
    diTreeBuilder: DiTreeBuilder.() -> Unit,
) {
    val diTree: DiTree = rememberDiTree(
        viewModelKey = SCULPTOR_SCREEN_DI_TREE_KEY,
        diTreeBuilder = { SculptorBuilderImpl(globalDiTree = globalDiTree).apply(diTreeBuilder) },
    )
    val store: Store<SculptorState, SculptorEvent> = rememberStore(
        viewModelKey = SCULPTOR_SCREEN_STORE_KEY,
        factory = { diTree.get(SculptorStore::class) }
    )
    SculptorScreenImpl(
        intent = intent,
        diTree = { diTree },
        store = { store },
        loadingScreen = loadingScreen,
        errorScreen = errorScreen,
        modifier = modifier,
    )
}

private const val SCULPTOR_SCREEN_STORE_KEY: String = "SCULPTOR_STORE_KEY"
private const val SCULPTOR_SCREEN_DI_TREE_KEY: String = "SCULPTOR_DI_TREE_KEY"
