package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.Identifier
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Arrangement

@Serializable
@SerialName("column@state")
public data class ColumnState(
    @SerialName("vertical_arrangement")
    val verticalArrangement: Arrangement.Vertical = Arrangement.Vertical.Top,
    @SerialName("horizontal_alignment")
    val horizontalAlignment: Alignment.Horizontal = Alignment.Horizontal.Start,
    @SerialName("content")
    val content: List<Identifier> = emptyList(),
) : StateContract
