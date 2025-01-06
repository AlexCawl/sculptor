package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.alignment.AlignmentWrapper
import org.alexcawl.skulptor.core.modifier.SkulptorModifier

@Serializable
@SerialName("foundation@box")
data class BoxLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifier")
    override val modifier: List<SkulptorModifier>,
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
    override fun buildLayout(
        scope: Any,
        modifier: Modifier,
        onChild: (child: SkulptorLayout, parentScope: Any) -> Unit
    ): @Composable () -> Unit = {
        Box(
            modifier = modifier.testTag(tag = id),
            contentAlignment = state.contentAlignment?.asCompose() ?: Alignment.TopStart,
            propagateMinConstraints = state.propagateMinConstraints ?: false,
            content = {
                when (val child = state.content) {
                    null -> Unit
                    else -> onChild(child, this)
                }
            }
        )
    }
}
