package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.provider.AlignmentProvider
import org.alexcawl.skulptor.core.provider.ArrangementProvider

@Serializable
@SerialName("foundation@column")
data class ColumnLayout(
    override val id: String,
    override val modifiers: List<@Contextual SkulptorModifier>,
    val state: State
) : SkulptorLayout() {
    @Serializable
    data class State(
        @SerialName("vertical_arrangement")
        val verticalArrangement: ArrangementProvider.Vertical? = null,
        @SerialName("horizontal_alignment")
        val horizontalAlignment: AlignmentProvider.Horizontal? = null,
        @SerialName("content")
        val content: List<@Contextual SkulptorLayout>? = null
    )

    override fun Scope.build(): @Composable () -> Unit = {
        val modifier = carve(modifiers)
        Column(
            modifier = modifier,
            verticalArrangement = state.verticalArrangement?.invoke() ?: Arrangement.Top,
            horizontalAlignment = state.horizontalAlignment?.invoke() ?: Alignment.Start,
            content = {
                state.content?.forEach {
                    this.place(it)
                }
            }
        )
    }
}
