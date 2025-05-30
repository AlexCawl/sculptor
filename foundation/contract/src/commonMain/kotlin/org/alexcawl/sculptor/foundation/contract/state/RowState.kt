package org.alexcawl.sculptor.foundation.contract.state

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.Identifier
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Arrangement

@Serializable
@SerialName("row@state")
public data class RowState(
    @SerialName("horizontal_arrangement")
    val horizontalArrangement: Arrangement.Horizontal = Arrangement.Horizontal.Start,
    @SerialName("vertical_alignment")
    val verticalAlignment: Alignment.Vertical = Alignment.Vertical.Top,
    @SerialName("content")
    val content: List<Identifier> = emptyList(),
) : StateContract
