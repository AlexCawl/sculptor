package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.ComponentLayout
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.ContainerLayout
import org.alexcawl.skulptor.core.provider.AlignmentProvider
import org.alexcawl.skulptor.core.provider.ArrangementProvider

@Serializable
@SerialName("foundation@row")
data class RowLayout(
    override val id: String,
    override val modifiers: List<@Contextual SkulptorModifier>,
    val state: State
) : ContainerLayout() {
    @Serializable
    data class State(
        val horizontalArrangement: ArrangementProvider.Horizontal? = null,
        val verticalAlignment: AlignmentProvider.Vertical? = null,
        val content: List<@Contextual ComponentLayout>? = null
    )

    override fun Scope.build(): @Composable () -> Unit = {
        val modifier = carve(modifiers)
        Row(
            modifier = modifier,
            horizontalArrangement = state.horizontalArrangement?.invoke() ?: Arrangement.Start,
            verticalAlignment = state.verticalAlignment?.invoke() ?: Alignment.Top,
            content = {
                state.content?.forEach {
                    this.place(it)
                }
            }
        )
    }
}
