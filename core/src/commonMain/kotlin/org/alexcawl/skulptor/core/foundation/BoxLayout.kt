package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.provider.AlignmentProvider

@Serializable
@SerialName("foundation@box")
data class BoxLayout(
    override val id: String,
    override val modifiers: List<@Contextual SkulptorModifier>,
    val state: State
) : SkulptorLayout() {
    @Serializable
    data class State(
        @SerialName("content_alignment")
        val contentAlignment: AlignmentProvider.HorizontalAndVertical? = null,
        @SerialName("propagate_min_constraints")
        val propagateMinConstraints: Boolean? = null,
        @SerialName("content")
        val content: @Contextual SkulptorLayout? = null,
    )

    override fun Scope.build(): @Composable () -> Unit = {
        val modifier = carve(modifiers)
        Box(
            modifier = modifier,
            contentAlignment = state.contentAlignment?.invoke() ?: Alignment.TopStart,
            propagateMinConstraints = state.propagateMinConstraints ?: false,
            content = {
                when (val child = state.content) {
                    null -> Unit
                    else -> this.place(child)
                }
            }
        )
    }
}
