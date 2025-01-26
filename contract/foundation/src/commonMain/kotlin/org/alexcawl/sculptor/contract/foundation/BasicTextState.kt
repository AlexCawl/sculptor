package org.alexcawl.sculptor.contract.foundation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.common.BaseState

@Serializable
@SerialName("state@basic_text")
data class BasicTextState(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<BaseModifier>,
    @SerialName("text")
    val text: String,
    @SerialName("soft_wrap")
    val softWrap: Boolean,
    @SerialName("max_lines")
    val maxLines: Int,
    @SerialName("min_lines")
    val minLines: Int,
) : BaseState {
    companion object {
        operator fun invoke(
            id: String,
            modifiers: List<BaseModifier> = listOf(),
            text: String,
            softWrap: Boolean = true,
            maxLines: Int = Int.MAX_VALUE,
            minLines: Int = 1
        ): BasicTextState = BasicTextState(
            id = id,
            modifiers = modifiers,
            text = text,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines
        )
    }
}
