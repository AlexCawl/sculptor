package org.alexcawl.skulptor.foundation.layout.basictext

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.ComponentLayout
import org.alexcawl.skulptor.core.SkulptorModifier

@Serializable
@SerialName("layout@basic_text")
data class BasicTextLayout(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<@Contextual SkulptorModifier>,
) : ComponentLayout<BasicTextState>() {
    override fun ComponentLayoutScope.build(): @Composable () -> Unit = {
        val state = getState<BasicTextState>(id)
        BasicText(
            text = state.text,
            modifier = carve(modifiers),
            softWrap = state.softWrap,
            maxLines = state.maxLines,
            minLines = state.minLines
        )
    }
}
