package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
data class RowLayout(
    override val id: String,
    override val modifier: List<SkulptorModifier>,
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
    override fun buildLayout(
        scope: Any,
        modifier: Modifier,
        onChild: (child: SkulptorLayout, parentScope: Any) -> Unit
    ): @Composable () -> Unit = {
        Row(
            modifier = modifier,
            horizontalArrangement = state.horizontalArrangement?.asCompose() ?: Arrangement.Start,
            verticalAlignment = state.verticalAlignment?.asCompose() ?: Alignment.Top,
            content = {
                state.content?.forEach {
                    onChild(it, this)
                }
            }
        )
    }
}
