package org.alexcawl.sculptor.runtime.engine.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.compose.rememberDiTree
import org.alexcawl.sculptor.internal.mvi.compose.rememberStore
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.runtime.engine.SculptorIntent
import org.alexcawl.sculptor.runtime.engine.SculptorScreen
import org.alexcawl.sculptor.runtime.engine.di.SculptorConnector
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState
import org.alexcawl.sculptor.runtime.engine.domain.SculptorStore

internal class SculptorScreenRememberImpl(
    private val sculptorConnector: SculptorConnector,
) : SculptorScreen {
    @Composable
    override fun open(
        intent: SculptorIntent,
        loadingScreen: @Composable (modifier: Modifier) -> Unit,
        errorScreen: @Composable (modifier: Modifier) -> Unit,
        modifier: Modifier,
    ) {
        val diTree: DiTree = rememberDiTree(
            viewModelKey = REMEMBER_DI_TREE_KEY,
            factory = sculptorConnector::localDiTree,
        )
        val store: Store<SculptorState, SculptorEvent> = rememberStore(
            viewModelKey = REMEMBER_STORE_KEY,
            factory = { diTree.get(SculptorStore::class) },
        )
        Content(
            intent = intent,
            diTree = { diTree },
            store = { store },
            loadingScreen = loadingScreen,
            errorScreen = errorScreen,
            modifier = modifier,
        )
    }

    @Composable
    override fun provides(content: @Composable () -> Unit) {
        val diTree: DiTree = rememberDiTree(
            viewModelKey = REMEMBER_DI_TREE_KEY,
            factory = sculptorConnector::localDiTree,
        )
        val store: Store<SculptorState, SculptorEvent> = rememberStore(
            viewModelKey = REMEMBER_STORE_KEY,
            factory = { diTree.get(SculptorStore::class) },
        )
        Scope(
            diTree = { diTree },
            store = { store },
            content = content,
        )
    }
}

private const val REMEMBER_STORE_KEY = "REMEMBER_STORE_KEY"
private const val REMEMBER_DI_TREE_KEY = "REMEMBER_DI_TREE_KEY"
