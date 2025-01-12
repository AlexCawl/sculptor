package org.alexcawl.skulptor.core.foundation

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
    override val id: String,
    override val modifiers: List<@Contextual SkulptorModifier>,
) : ComponentLayout() {
    @Serializable
    data class State(
        override val id: String,
        val text: String? = null,
        val softWrap: Boolean? = null,
        val maxLines: Int? = null,
        val minLines: Int? = null,
    ) : BaseState

    override fun Scope.build(): @Composable () -> Unit = {
        val modifier = carve(modifiers)
        val state = getState<State>(id) ?: error("Dudes no state")
        BasicText(
            text = state.text ?: "",
            modifier = modifier,
            softWrap = state.softWrap ?: true,
            maxLines = state.maxLines ?: Int.MAX_VALUE,
            minLines = state.minLines ?: 1
        )
    }
}
