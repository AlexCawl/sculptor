package org.alexcawl.skulptor.foundation.layout.box

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.util.fastForEach
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.ContainerLayout
import org.alexcawl.skulptor.core.SkulptorModifier

@Serializable
@SerialName("layout@box")
data class BoxLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<@Contextual SkulptorModifier>,
) : ContainerLayout<BoxState>() {
    override fun ContainerLayoutScope.build(): @Composable () -> Unit = {
        val state = getState<BoxState>(id)
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
}
