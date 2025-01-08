package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.provider.AlignmentProvider
import org.alexcawl.skulptor.core.provider.ArrangementProvider

@Serializable
@SerialName("foundation@column")
data class RowLayout(
    override val id: String,
    override val modifiers: List<SkulptorModifier>,
    override val state: State
) : SkulptorLayout {
    @Serializable
    data class State(
        @SerialName("horizontal_arrangement")
        val horizontalArrangement: ArrangementProvider.Horizontal? = null,
        @SerialName("vertical_alignment")
        val verticalAlignment: AlignmentProvider.Vertical? = null,
        @SerialName("content")
        val content: List<SkulptorLayout>? = null
    )

    override fun build(skulptor: Skulptor, scope: Any): @Composable () -> Unit = {
        val modifier = skulptor.carve(modifiers)
        Row(
            modifier = modifier,
            horizontalArrangement = state.horizontalArrangement?.invoke() ?: Arrangement.Start,
            verticalAlignment = state.verticalAlignment?.invoke() ?: Alignment.Top,
            content = {
                state.content?.forEach {
                    skulptor.place(it, this)
                }
            }
        )
    }
}
