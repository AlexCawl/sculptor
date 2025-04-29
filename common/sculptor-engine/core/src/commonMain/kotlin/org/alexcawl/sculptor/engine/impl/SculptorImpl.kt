package org.alexcawl.sculptor.engine.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow
import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererScope
import org.alexcawl.sculptor.engine.Sculptor
import org.alexcawl.sculptor.engine.SculptorInteractor
import org.alexcawl.sculptor.engine.SculptorState
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.get

internal class SculptorImpl(diTree: DiTree) : Sculptor {
    private val sculptorInteractor: SculptorInteractor by lazy<SculptorInteractor>(diTree::get)
    private val rendererScope: RendererScope by lazy<RendererScope>(diTree::get)

    @Composable
    override fun open(
        deeplink: String,
        loadingScreen: @Composable (modifier: Modifier) -> Unit,
        errorScreen: @Composable (modifier: Modifier) -> Unit,
        modifier: Modifier
    ) {
        LaunchedEffect(deeplink) {
            sculptorInteractor.handle(deeplink = deeplink)
        }
        val state: SculptorState by sculptorInteractor.state.collectAsState()
        when (val stateValue = state) {
            is SculptorState.Content -> {
                val renderer: Renderer<UiState> = sculptorInteractor.renderer(layout = stateValue.layout)
                renderer.Draw(
                    scope = rendererScope,
                    id = stateValue.layout.id,
                    modifier = modifier.then(stateValue.layout.modifier),
                    state = stateValue.layout.uiState,
                )
            }
            SculptorState.Error -> errorScreen(modifier)
            SculptorState.Loading -> loadingScreen(modifier)
        }
    }
}
