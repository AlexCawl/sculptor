package org.alexcawl.sculptor.foundation.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Contract
import org.alexcawl.sculptor.common.contract.ContractModifier
import org.alexcawl.sculptor.foundation.contract.property.Alignment
import org.alexcawl.sculptor.foundation.contract.property.Arrangement

@Serializable
@SerialName("state@column")
data class ColumnContract(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifier: List<ContractModifier>,
    @SerialName("vertical_arrangement")
    val verticalArrangement: Arrangement.Vertical,
    @SerialName("horizontal_alignment")
    val horizontalAlignment: Alignment.Horizontal,
    @SerialName("content")
    val content: List<String>,
) : Contract
