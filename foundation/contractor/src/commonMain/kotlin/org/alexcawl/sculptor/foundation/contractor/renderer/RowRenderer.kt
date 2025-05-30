package org.alexcawl.sculptor.foundation.contractor.renderer

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.alexcawl.sculptor.core.contractor.layout.Layout
import org.alexcawl.sculptor.core.contractor.renderer.Renderer
import org.alexcawl.sculptor.core.contractor.renderer.RendererScope
import org.alexcawl.sculptor.foundation.contractor.state.RowUiState
import kotlin.reflect.KClass

internal class RowRenderer : Renderer<RowUiState>() {
    override val state: KClass<RowUiState> = RowUiState::class

    @Composable
    override fun Draw(
        scope: RendererScope,
        id: String,
        modifier: Modifier,
        state: RowUiState,
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
