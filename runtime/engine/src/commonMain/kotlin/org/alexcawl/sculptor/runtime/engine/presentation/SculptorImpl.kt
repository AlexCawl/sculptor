package org.alexcawl.sculptor.runtime.engine.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.DiTreeBuilder
import org.alexcawl.sculptor.internal.di.compose.diTree
import org.alexcawl.sculptor.internal.mvi.compose.store
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.SculptorIntent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState
import org.alexcawl.sculptor.runtime.engine.domain.SculptorStore

internal class SculptorImpl(
    viewModelStoreOwner: ViewModelStoreOwner,
    diTreeBuilder: DiTreeBuilder,
) : Sculptor, ViewModelStoreOwner by viewModelStoreOwner {
    private val diTree: DiTree by diTree(
        viewModelKey = SCULPTOR_DI_TREE_KEY,
        diTreeBuilder = diTreeBuilder,
    )
    private val store: Store<SculptorState, SculptorEvent> by store(
        viewModelKey = SCULPTOR_STORE_KEY,
        factory = { diTree.get(SculptorStore::class) },
    )

    @Composable
    override fun open(
        intent: SculptorIntent,
        loadingScreen: @Composable (modifier: Modifier) -> Unit,
        errorScreen: @Composable (modifier: Modifier) -> Unit,
        modifier: Modifier,
    ) = SculptorScreenImpl(
        intent = intent,
        diTree = { diTree },
        store = { store },
        loadingScreen = loadingScreen,
        errorScreen = errorScreen,
        modifier = modifier,
    )

    @Composable
    override fun provides(content: @Composable () -> Unit) {
        CompositionLocalProvider(
            CompositionLocalDiTree provides diTree,
            CompositionLocalStore provides store,
            content = content,
        )
    }
}

private const val SCULPTOR_STORE_KEY = "SCULPTOR_STORE_KEY"
private const val SCULPTOR_DI_TREE_KEY = "SCULPTOR_DI_TREE_KEY"
