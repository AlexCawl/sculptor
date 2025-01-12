package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.util.fastForEach
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.ContainerLayout
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.provider.AlignmentProvider

@Serializable
@SerialName("foundation@box")
data class BoxLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<@Contextual SkulptorModifier>,
) : ContainerLayout() {
    override fun ContainerLayoutScope.build(): @Composable () -> Unit = {
        val state = getState<State>(id)
        Box(
            modifier = carve(modifiers),
            contentAlignment = state.contentAlignment.invoke(),
            propagateMinConstraints = state.propagateMinConstraints,
            content = {
                state.content.mapNotNull(::getLayoutOrNull).fastForEach {
                    this.place(it)
                }
            }
        )
    }

    @Serializable
    data class State(
        @SerialName("id")
        override val id: String,
        @SerialName("content_alignment")
        val contentAlignment: AlignmentProvider.HorizontalAndVertical = AlignmentProvider.HorizontalAndVertical(Alignment.TopStart),
        @SerialName("propagate_min_constraints")
        val propagateMinConstraints: Boolean = false,
        @SerialName("content")
        val content: List<String> = listOf(),
    ) : BaseState
}
