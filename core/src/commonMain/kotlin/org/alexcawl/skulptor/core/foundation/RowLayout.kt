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
import org.alexcawl.skulptor.core.attribute.AlignmentWrapper
import org.alexcawl.skulptor.core.attribute.ArrangementWrapper

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
        val horizontalArrangement: ArrangementWrapper.Horizontal? = null,
        @SerialName("vertical_alignment")
        val verticalAlignment: AlignmentWrapper.Vertical? = null,
        @SerialName("content")
        val content: List<SkulptorLayout>? = null
    )

    @Composable
    override fun Skulptor.build(scope: Any): @Composable () -> Unit = {
        val modifier = carve(scope, modifiers)
        Row(
            modifier = modifier,
            horizontalArrangement = state.horizontalArrangement?.asCompose() ?: Arrangement.Start,
            verticalAlignment = state.verticalAlignment?.asCompose() ?: Alignment.Top,
            content = {
                state.content?.forEach {
                    place(this, it)
                }
            }
        )
    }
}
