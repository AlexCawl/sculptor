package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.alignment.AlignmentWrapper
import org.alexcawl.skulptor.core.arrangement.ArrangementWrapper
import org.alexcawl.skulptor.core.modifier.SkulptorModifier

@Serializable
@SerialName("foundation@column")
data class ColumnLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifier")
    override val modifier: List<SkulptorModifier>,
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
    override fun buildLayout(
        scope: Any,
        modifier: Modifier,
        onChild: (child: SkulptorLayout, parentScope: Any) -> Unit
    ): @Composable () -> Unit = {
        Column(
            modifier = modifier,
            verticalArrangement = state.verticalArrangement?.asCompose() ?: Arrangement.Top,
            horizontalAlignment = state.horizontalAlignment?.asCompose() ?: Alignment.Start,
            content = {
                state.content?.forEach {
                    onChild(it, this)
                }
            }
        )
    }
}
