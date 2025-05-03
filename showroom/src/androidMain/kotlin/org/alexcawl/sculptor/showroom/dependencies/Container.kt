package org.alexcawl.sculptor.showroom.dependencies

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.Identifier
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.core.layout.Layout
import org.alexcawl.sculptor.core.layout.UiState
import org.alexcawl.sculptor.core.presenter.PresenterScope
import org.alexcawl.sculptor.core.presenter.StatePresenter
import org.alexcawl.sculptor.core.renderer.Renderer
import org.alexcawl.sculptor.core.renderer.RendererScope
import kotlin.reflect.KClass

@Serializable
@SerialName("container")
internal data class ContainerState(
    @SerialName("content")
    val content: List<Identifier>,
) : StateContract

@Immutable
internal data class ContainerUiState(
    val content: List<Layout>,
) : UiState

internal object ContainerPresenter : StatePresenter<ContainerState>() {
    override val input: KClass<ContainerState> = ContainerState::class

    override suspend fun PresenterScope.dslTransform(input: ContainerState): UiState {
        return ContainerUiState(content = layouts(input = input.content))
    }
}

internal object ContainerRenderer : Renderer<ContainerUiState>() {
    override val state: KClass<ContainerUiState> = ContainerUiState::class

    @Composable
    override fun Draw(
        scope: RendererScope,
        id: String,
        modifier: Modifier,
        state: ContainerUiState,
    ) {
        Column(
            modifier = modifier.testTag(tag = id),
        ) {
            state.content.forEach { layout: Layout ->
                scope.draw(layout = layout)
            }
        }
    }
}
