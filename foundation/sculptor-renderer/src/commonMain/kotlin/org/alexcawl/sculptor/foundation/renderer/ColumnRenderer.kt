package org.alexcawl.sculptor.foundation.renderer

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererScope
import org.alexcawl.sculptor.foundation.layout.ColumnUiState
import kotlin.reflect.KClass

@Stable
public class ColumnRenderer : Renderer<ColumnUiState>() {
    override val state: KClass<ColumnUiState> = ColumnUiState::class

    @Composable
    public override fun Draw(
        scope: RendererScope,
        id: String,
        modifier: Modifier,
        state: ColumnUiState
    ): Unit = with(scope) {
        Column(
            modifier = modifier.testTag(tag = id),
            verticalArrangement = state.verticalArrangement,
            horizontalAlignment = state.horizontalAlignment,
            content = {
                state.content.forEach { layout: Layout ->
                    draw(layout)
                }
            }
        )
    }
}
