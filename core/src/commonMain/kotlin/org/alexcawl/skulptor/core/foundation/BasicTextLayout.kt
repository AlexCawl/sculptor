package org.alexcawl.skulptor.core.foundation

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.modifier.SkulptorModifier

@Serializable
@SerialName("foundation@basic_text")
data class BasicTextLayout(
    override val id: String,
    override val modifier: List<SkulptorModifier>,
    override val state: State
) : SkulptorLayout {
    @Serializable
    sealed interface State {
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

    @Composable
    override fun buildLayout(
        scope: Any,
        modifier: Modifier,
        onChild: (child: SkulptorLayout, parentScope: Any) -> Unit
    ): @Composable () -> Unit = {
        when (state) {
            is State.Base -> {
                BasicText(
                    text = state.text ?: "",
                    modifier = modifier.testTag(id),
                    softWrap = state.softWrap ?: true,
                    maxLines = state.maxLines ?: Int.MAX_VALUE,
                    minLines = state.minLines ?: 1
                )
            }
        }
    }
}
