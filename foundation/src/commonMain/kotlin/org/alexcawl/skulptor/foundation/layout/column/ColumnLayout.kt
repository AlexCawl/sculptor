package org.alexcawl.skulptor.foundation.layout.column

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.util.fastForEach
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseLayout
import org.alexcawl.skulptor.core.ContainerLayout
import org.alexcawl.skulptor.core.SkulptorModifier

@Serializable
@SerialName("layout@column")
data class ColumnLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<@Contextual SkulptorModifier>,
) : ContainerLayout<ColumnState>() {
    override fun ContainerLayoutScope.build(): @Composable () -> Unit = {
        val state = getState<ColumnState>(id)
        Column(
            modifier = carve(modifiers),
            verticalArrangement = state.verticalArrangement.invoke(),
            horizontalAlignment = state.horizontalAlignment.invoke(),
            content = {
                state.content.map<String, BaseLayout<*>>(::getLayout).fastForEach {
                    this.place(it)
                }
            }
        )
    }
}
