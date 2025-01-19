package org.alexcawl.skulptor.foundation.layout.row

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.util.fastForEach
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseLayout
import org.alexcawl.skulptor.core.ContainerLayout
import org.alexcawl.skulptor.core.SkulptorModifier

@Serializable
@SerialName("layout@row")
data class RowLayout(
    override val id: String,
    override val modifiers: List<@Contextual SkulptorModifier>,
) : ContainerLayout<RowState>() {
    override fun ContainerLayoutScope.build(): @Composable () -> Unit = {
        val state = getState<RowState>(id)
        Row(
            modifier = carve(modifiers),
            horizontalArrangement = state.horizontalArrangement.invoke(),
            verticalAlignment = state.verticalAlignment.invoke(),
            content = {
                state.content.map<String, BaseLayout<*>>(::getLayout).fastForEach {
                    this.place(it)
                }
            }
        )
    }
}
