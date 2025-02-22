package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.foundation.contract.common.Alignment
import org.alexcawl.sculptor.foundation.contract.common.Arrangement

@Serializable
@SerialName("row@state")
public data class RowState(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("horizontal_arrangement")
    val horizontalArrangement: Arrangement.Horizontal,
    @SerialName("vertical_alignment")
    val verticalAlignment: Alignment.Vertical,
    @SerialName("content")
    val content: List<Identifier>,
) : StateContract
