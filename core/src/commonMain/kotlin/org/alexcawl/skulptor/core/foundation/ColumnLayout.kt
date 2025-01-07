package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
data class ColumnLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifier")
    override val modifiers: List<SkulptorModifier>,
    @SerialName("state")
    override val state: State
) : SkulptorLayout {
    @Serializable
    data class State(
        @SerialName("vertical_arrangement")
        val verticalArrangement: ArrangementWrapper.Vertical? = null,
        @SerialName("horizontal_alignment")
        val horizontalAlignment: AlignmentWrapper.Horizontal? = null,
        @SerialName("content")
        val content: List<SkulptorLayout>? = null
    )

    @Composable
    override fun Skulptor.build(scope: Any): @Composable () -> Unit = {
        val modifier = carve(scope, modifiers)
        Column(
            modifier = modifier,
            verticalArrangement = state.verticalArrangement?.asCompose() ?: Arrangement.Top,
            horizontalAlignment = state.horizontalAlignment?.asCompose() ?: Alignment.Start,
            content = {
                state.content?.forEach {
                    place(this, it)
                }
            }
        )
    }
}
