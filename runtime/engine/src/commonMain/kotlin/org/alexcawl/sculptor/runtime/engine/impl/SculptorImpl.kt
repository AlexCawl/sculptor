package org.alexcawl.sculptor.runtime.engine.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.renderer.Renderer
import org.alexcawl.sculptor.core.renderer.RendererScope
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.internal.di.get
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.SculptorInteractor
import org.alexcawl.sculptor.runtime.engine.SculptorState

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
            is SculptorState.Error -> errorScreen(modifier)
            is SculptorState.Loading -> loadingScreen(modifier)
            else -> error("Unknown state")
        }
    }
}
