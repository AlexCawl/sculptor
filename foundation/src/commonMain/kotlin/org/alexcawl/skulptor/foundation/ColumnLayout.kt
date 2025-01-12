package org.alexcawl.skulptor.foundation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.util.fastForEach
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseLayout
import org.alexcawl.skulptor.core.BaseState
import org.alexcawl.skulptor.core.ContainerLayout
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.provider.AlignmentProvider
import org.alexcawl.skulptor.provider.ArrangementProvider

@Serializable
@SerialName("foundation@column")
data class ColumnLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<@Contextual SkulptorModifier>,
) : ContainerLayout() {
    override fun ContainerLayoutScope.build(): @Composable () -> Unit = {
        val state = getState<State>(id)
        Column(
            modifier = carve(modifiers),
            verticalArrangement = state.verticalArrangement.invoke(),
            horizontalAlignment = state.horizontalAlignment.invoke(),
            content = {
                state.content.map<String, BaseLayout>(::getLayout).fastForEach {
                    this.place(it)
                }
            }
        )
    }

    @Serializable
    data class State(
        @SerialName("id")
        override val id: String,
        @SerialName("vertical_arrangement")
        val verticalArrangement: ArrangementProvider.Vertical = ArrangementProvider.Vertical.Top,
        @SerialName("horizontal_alignment")
        val horizontalAlignment: AlignmentProvider.Horizontal = AlignmentProvider.Horizontal(Alignment.Start),
        @SerialName("content")
        val content: List<String> = listOf()
    ) : BaseState
}
