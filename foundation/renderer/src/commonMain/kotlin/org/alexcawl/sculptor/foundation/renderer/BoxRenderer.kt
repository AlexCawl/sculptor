package org.alexcawl.sculptor.foundation.renderer

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.core.renderer.Renderer
import org.alexcawl.sculptor.core.renderer.RendererScope
import org.alexcawl.sculptor.foundation.layout.BoxUiState
import kotlin.reflect.KClass

@Stable
public class BoxRenderer : Renderer<BoxUiState>() {
    override val state: KClass<BoxUiState> = BoxUiState::class

    @Composable
    public override fun Draw(
        scope: RendererScope,
        id: String,
        modifier: Modifier,
        state: BoxUiState
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
