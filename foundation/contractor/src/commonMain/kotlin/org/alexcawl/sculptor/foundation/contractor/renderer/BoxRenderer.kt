package org.alexcawl.sculptor.foundation.contractor.renderer

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.alexcawl.sculptor.core.contractor.layout.Layout
import org.alexcawl.sculptor.core.contractor.renderer.Renderer
import org.alexcawl.sculptor.core.contractor.renderer.RendererScope
import org.alexcawl.sculptor.foundation.contractor.state.BoxUiState
import kotlin.reflect.KClass

internal class BoxRenderer : Renderer<BoxUiState>() {
    override val state: KClass<BoxUiState> = BoxUiState::class

    @Composable
    override fun Draw(
        scope: RendererScope,
        id: String,
        modifier: Modifier,
        state: BoxUiState,
    ): Unit = with(scope) {
        Box(
            modifier = modifier.testTag(tag = id),
            contentAlignment = state.contentAlignment,
            propagateMinConstraints = state.propagateMinConstraints,
            content = {
                state.content.forEach { layout: Layout ->
                    draw(layout)
                }
            }
        )
    }
}
