package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseLayout
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.ContainerLayout
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.provider.AlignmentProvider

@Serializable
@SerialName("foundation@box")
data class BoxLayout(
    override val id: String,
    override val modifiers: List<@Contextual SkulptorModifier>,
) : ContainerLayout() {
    @Serializable
    data class State(
        override val id: String,
        val contentAlignment: AlignmentProvider.HorizontalAndVertical? = null,
        val propagateMinConstraints: Boolean? = null,
        val content: String?,
    ) : BaseState

    override fun Scope.build(): @Composable () -> Unit = {
        val modifier = carve(modifiers)
        val state = getState<State>(id) ?: error("Dudes no state")
        Box(
            modifier = modifier,
            contentAlignment = state.contentAlignment?.invoke() ?: Alignment.TopStart,
            propagateMinConstraints = state.propagateMinConstraints ?: false,
            content = {
                when (val childId = state.content) {
                    null -> Unit
                    else -> {
                        val child = getLayout<BaseLayout>(childId) ?: error("Dudes no layout")
                        this.place(child)
                    }
                }
            }
        )
    }
}
