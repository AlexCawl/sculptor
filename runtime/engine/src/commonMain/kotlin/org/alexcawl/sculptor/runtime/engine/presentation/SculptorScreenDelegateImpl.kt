package org.alexcawl.sculptor.runtime.engine.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.compose.diTree
import org.alexcawl.sculptor.internal.mvi.compose.store
import org.alexcawl.sculptor.internal.mvi.core.Store
import org.alexcawl.sculptor.runtime.engine.SculptorIntent
import org.alexcawl.sculptor.runtime.engine.SculptorScreen
import org.alexcawl.sculptor.runtime.engine.di.SculptorConnector
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.domain.SculptorState
import org.alexcawl.sculptor.runtime.engine.domain.SculptorStore

internal class SculptorScreenDelegateImpl(
    sculptorConnector: SculptorConnector,
    viewModelStoreOwner: ViewModelStoreOwner,
) : SculptorScreen, ViewModelStoreOwner by viewModelStoreOwner {
    private val diTree: DiTree by diTree(
        viewModelKey = DELEGATE_DI_TREE_KEY,
        factory = { sculptorConnector.localDiTree },
    )
    private val store: Store<SculptorState, SculptorEvent> by store(
        viewModelKey = DELEGATE_STORE_KEY,
        factory = { diTree.get(SculptorStore::class) },
    )

    @Composable
    override fun open(
        intent: SculptorIntent,
        loadingScreen: @Composable (modifier: Modifier) -> Unit,
        errorScreen: @Composable (modifier: Modifier) -> Unit,
        modifier: Modifier,
    ) {
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
        Scope(
            diTree = { diTree },
            store = { store },
            content = content,
        )
    }
}

private const val DELEGATE_STORE_KEY = "DELEGATE_STORE_KEY"
private const val DELEGATE_DI_TREE_KEY = "DELEGATE_DI_TREE_KEY"
