package org.alexcawl.skulptor.foundation

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.ComponentLayout
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.BaseState

@Serializable
@SerialName("foundation@basic_text")
data class BasicTextLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<@Contextual SkulptorModifier>,
) : ComponentLayout() {
    override fun ComponentLayoutScope.build(): @Composable () -> Unit = {
        val state = getState<State>(id)
        BasicText(
            text = state.text,
            modifier = carve(modifiers),
            softWrap = state.softWrap,
            maxLines = state.maxLines,
            minLines = state.minLines
        )
    }

    @Serializable
    data class State(
        @SerialName("id")
        override val id: String,
        @SerialName("text")
        val text: String,
        @SerialName("soft_wrap")
        val softWrap: Boolean = true,
        @SerialName("max_lines")
        val maxLines: Int = Int.MAX_VALUE,
        @SerialName("min_lines")
        val minLines: Int = 1,
    ) : BaseState
}
