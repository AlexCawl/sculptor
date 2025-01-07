package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.attribute.AlignmentWrapper

@Serializable
@SerialName("foundation@box")
data class BoxLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifier")
    override val modifiers: List<SkulptorModifier>,
    @SerialName("state")
    override val state: State
) : SkulptorLayout {
    @Serializable
    data class State(
        @SerialName("content_alignment")
        val contentAlignment: AlignmentWrapper.HorizontalAndVertical? = null,
        @SerialName("propagate_min_constraints")
        val propagateMinConstraints: Boolean? = null,
        @SerialName("content")
        val content: SkulptorLayout? = null,
    )

    @Composable
    override fun Skulptor.build(scope: Any): @Composable () -> Unit = {
        val modifier = carve(scope, modifiers)
        Box(
            modifier = modifier,
            contentAlignment = state.contentAlignment?.asCompose() ?: Alignment.TopStart,
            propagateMinConstraints = state.propagateMinConstraints ?: false,
            content = {
                when (val child = state.content) {
                    null -> Unit
                    else -> place(this, child)
                }
            }
        )
    }
}
