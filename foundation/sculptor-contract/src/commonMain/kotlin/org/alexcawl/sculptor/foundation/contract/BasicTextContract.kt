package org.alexcawl.sculptor.foundation.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Contract
import org.alexcawl.sculptor.common.contract.ContractModifier

@Serializable
@SerialName("state@basic_text")
data class BasicTextContract(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifier: List<ContractModifier>,
    @SerialName("text")
    val text: String,
    @SerialName("soft_wrap")
    val softWrap: Boolean,
    @SerialName("max_lines")
    val maxLines: Int,
    @SerialName("min_lines")
    val minLines: Int,
) : Contract
