package org.alexcawl.sculptor.contract.foundation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.common.BaseState
import org.alexcawl.sculptor.contract.common.Alignment
import org.alexcawl.sculptor.contract.common.Arrangement

@Serializable
@SerialName("state@row")
data class RowState(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<BaseModifier>,
    @SerialName("horizontal_arrangement")
    val horizontalArrangement: Arrangement.Horizontal,
    @SerialName("vertical_alignment")
    val verticalAlignment: Alignment.Vertical,
    @SerialName("content")
    val content: List<String>
) : BaseState {
    companion object {
        operator fun invoke(
            id: String,
            modifiers: List<BaseModifier> = listOf(),
            horizontalArrangement: Arrangement.Horizontal = Arrangement.Horizontal.Start,
            verticalAlignment: Alignment.Vertical = Alignment.Vertical.Top,
            content: List<String>
        ): RowState = RowState(
            id = id,
            modifiers = modifiers,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
            content = content
        )
    }
}
