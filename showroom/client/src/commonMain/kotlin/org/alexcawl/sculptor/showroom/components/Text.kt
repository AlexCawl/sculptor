package org.alexcawl.sculptor.showroom.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.StatePresenter
import org.alexcawl.sculptor.core.renderer.Renderer
import org.alexcawl.sculptor.core.renderer.RendererScope
import kotlin.reflect.KClass

@Serializable
@SerialName("text")
internal data class TextState(
    @SerialName("text")
    val text: String,
) : StateContract

@Immutable
internal data class TextUiState(
    val text: String,
) : UiState

internal object TextPresenter : StatePresenter<TextState>() {
    override val input: KClass<TextState> = TextState::class

    override suspend fun PresenterScope.dslTransform(input: TextState): UiState {
        return TextUiState(text = input.text)
    }
}

internal object TextRenderer : Renderer<TextUiState>() {
    override val state: KClass<TextUiState> = TextUiState::class

    @Composable
    override fun Draw(
        scope: RendererScope,
        id: String,
        modifier: Modifier,
        state: TextUiState,
    ) {
        BasicText(
            modifier = modifier.testTag(tag = id),
            text = state.text,
        )
    }
}
