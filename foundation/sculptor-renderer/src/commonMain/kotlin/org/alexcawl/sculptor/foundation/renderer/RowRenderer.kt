package org.alexcawl.sculptor.foundation.renderer

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererScope
import org.alexcawl.sculptor.foundation.layout.RowUiState
import kotlin.reflect.KClass

@Stable
public class RowRenderer : Renderer<RowUiState>() {
    override val state: KClass<RowUiState> = RowUiState::class

    @Composable
    public override fun Draw(
        scope: RendererScope,
        id: String,
        modifier: Modifier,
        state: RowUiState
    ): Unit = with(scope) {
        Row(
            modifier = modifier.testTag(tag = id),
            horizontalArrangement = state.horizontalArrangement,
            verticalAlignment = state.verticalAlignment,
            content = {
                state.content.forEach { layout: Layout ->
                    draw(layout)
                }
            }
        )
    }
}
