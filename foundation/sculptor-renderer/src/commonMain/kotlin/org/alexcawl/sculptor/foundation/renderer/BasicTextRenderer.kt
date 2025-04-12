package org.alexcawl.sculptor.foundation.renderer

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.common.renderer.RendererScope
import org.alexcawl.sculptor.foundation.layout.BasicTextUiState
import kotlin.reflect.KClass

@Stable
public class BasicTextRenderer : Renderer<BasicTextUiState>() {
    override val state: KClass<BasicTextUiState> = BasicTextUiState::class

    @Composable
    override fun Draw(
        scope: RendererScope,
        id: String,
        modifier: Modifier,
        state: BasicTextUiState
    ) {
        BasicText(
            modifier = modifier.testTag(tag = id),
            text = state.text,
            softWrap = state.softWrap,
            maxLines = state.maxLines,
            minLines = state.minLines,
        )
    }
}
