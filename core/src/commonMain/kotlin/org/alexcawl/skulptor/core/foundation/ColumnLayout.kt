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
import org.alexcawl.skulptor.core.provider.ArrangementWrapper
import org.alexcawl.skulptor.core.provider.alignment.AlignmentHorizontalSerializable

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
        val horizontalAlignment: AlignmentHorizontalSerializable? = null,
        @SerialName("content")
        val content: List<SkulptorLayout>? = null
    )

    override fun build(skulptor: Skulptor, scope: Any): @Composable () -> Unit = {
        val modifier = skulptor.carve(modifiers)
        Column(
            modifier = modifier,
            verticalArrangement = state.verticalArrangement?.invoke() ?: Arrangement.Top,
            horizontalAlignment = state.horizontalAlignment ?: Alignment.Start,
            content = {
                state.content?.forEach {
                    skulptor.place(it, this)
                }
            }
        )
    }
}
