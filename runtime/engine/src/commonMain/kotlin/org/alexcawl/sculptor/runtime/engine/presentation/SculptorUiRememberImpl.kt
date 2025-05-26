package org.alexcawl.sculptor.runtime.engine.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.compose.rememberDiTree
import org.alexcawl.sculptor.internal.mvi.compose.rememberStore
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.runtime.engine.SculptorIntent
import org.alexcawl.sculptor.runtime.engine.SculptorUi
import org.alexcawl.sculptor.runtime.engine.di.SculptorConnector
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState
import org.alexcawl.sculptor.runtime.engine.domain.SculptorStore

internal class SculptorUiRememberImpl(
    private val sculptorConnector: SculptorConnector,
) : SculptorUi {
    @Composable
    override fun Screen(
        intent: SculptorIntent,
        loadingScreen: @Composable () -> Unit,
        errorScreen: @Composable () -> Unit,
        modifier: Modifier
    ) {
        val diTree: DiTree = rememberDiTree(
            viewModelKey = REMEMBER_DI_TREE_KEY,
            factory = sculptorConnector::localDiTree,
        )
        val store: Store<SculptorState, SculptorEvent> = rememberStore(
            viewModelKey = REMEMBER_STORE_KEY,
            factory = { diTree.get(SculptorStore::class) },
        )
        SculptorUiImpl(
            intent = intent,
            diTree = { diTree },
            store = { store },
            loadingScreen = loadingScreen,
            errorScreen = errorScreen,
            modifier = modifier,
        )
    }
}

private const val REMEMBER_STORE_KEY = "REMEMBER_STORE_KEY"
private const val REMEMBER_DI_TREE_KEY = "REMEMBER_DI_TREE_KEY"
