package org.alexcawl.sculptor.foundation.contractor.renderer

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.alexcawl.sculptor.core.contractor.layout.Layout
import org.alexcawl.sculptor.core.contractor.renderer.Renderer
import org.alexcawl.sculptor.core.contractor.renderer.RendererScope
import org.alexcawl.sculptor.foundation.contractor.state.ColumnUiState
import kotlin.reflect.KClass

internal class ColumnRenderer : Renderer<ColumnUiState>() {
    override val state: KClass<ColumnUiState> = ColumnUiState::class

    @Composable
    override fun Draw(
        scope: RendererScope,
        id: String,
        modifier: Modifier,
        state: ColumnUiState,
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
