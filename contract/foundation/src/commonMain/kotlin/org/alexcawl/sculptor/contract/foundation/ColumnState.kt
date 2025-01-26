package org.alexcawl.sculptor.contract.foundation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.common.BaseState
import org.alexcawl.sculptor.contract.common.Alignment
import org.alexcawl.sculptor.contract.common.Arrangement

@Serializable
@SerialName("state@column")
data class ColumnState(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifiers: List<BaseModifier>,
    @SerialName("vertical_arrangement")
    val verticalArrangement: Arrangement.Vertical,
    @SerialName("horizontal_alignment")
    val horizontalAlignment: Alignment.Horizontal,
    @SerialName("content")
    val content: List<String>,
) : BaseState {
    companion object {
        operator fun invoke(
            id: String,
            modifiers: List<BaseModifier> = listOf(),
            verticalArrangement: Arrangement.Vertical = Arrangement.Vertical.Top,
            horizontalAlignment: Alignment.Horizontal = Alignment.Horizontal.Start,
            content: List<String>
        ): ColumnState = ColumnState(
            id = id,
            modifiers = modifiers,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            content = content
        )
    }
}
