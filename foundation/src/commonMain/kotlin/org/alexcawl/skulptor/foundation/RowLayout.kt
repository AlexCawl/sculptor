package org.alexcawl.skulptor.foundation

import androidx.compose.foundation.layout.Row
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
@SerialName("foundation@row")
data class RowLayout(
    override val id: String,
    override val modifiers: List<@Contextual SkulptorModifier>,
) : ContainerLayout() {
    override fun ContainerLayoutScope.build(): @Composable () -> Unit = {
        val state = getState<State>(id)
        Row(
            modifier = carve(modifiers),
            horizontalArrangement = state.horizontalArrangement.invoke(),
            verticalAlignment = state.verticalAlignment.invoke(),
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
        @SerialName("horizontal_arrangement")
        val horizontalArrangement: ArrangementProvider.Horizontal = ArrangementProvider.Horizontal.Start,
        @SerialName("vertical_alignment")
        val verticalAlignment: AlignmentProvider.Vertical = AlignmentProvider.Vertical(Alignment.Top),
        @SerialName("content")
        val content: List<String> = listOf()
    ) : BaseState
}
