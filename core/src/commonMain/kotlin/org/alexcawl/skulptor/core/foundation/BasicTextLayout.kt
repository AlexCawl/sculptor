package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.SkulptorModifier

@Serializable
@SerialName("foundation@basic_text")
data class BasicTextLayout(
    override val id: String,
    override val modifiers: List<@Contextual SkulptorModifier>,
    val state: State
) : SkulptorLayout() {
    @Serializable
    sealed interface State {
        @Serializable
        @SerialName("text_state@base")
        data class Base(
            @SerialName("text")
            val text: String? = null,
            @SerialName("soft_wrap")
            val softWrap: Boolean? = null,
            @SerialName("max_lines")
            val maxLines: Int? = null,
            @SerialName("min_lines")
            val minLines: Int? = null
        ) : State
    }

    override fun Scope.build(): @Composable () -> Unit = {
        val modifier = carve(modifiers)
        when (state) {
            is State.Base -> {
                BasicText(
                    text = state.text ?: "",
                    modifier = modifier,
                    softWrap = state.softWrap ?: true,
                    maxLines = state.maxLines ?: Int.MAX_VALUE,
                    minLines = state.minLines ?: 1
                )
            }
        }
    }
}
