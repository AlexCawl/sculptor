package org.alexcawl.sculptor.foundation.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Contract
import org.alexcawl.sculptor.common.contract.ContractModifier
import org.alexcawl.sculptor.foundation.contract.property.Alignment

@Serializable
@SerialName("state@box")
data class BoxContract(
    @SerialName("id")
    override val id: String,
    @SerialName("modifiers")
    override val modifier: List<ContractModifier>,
    @SerialName("content_alignment")
    val contentAlignment: Alignment,
    @SerialName("propagate_min_constraints")
    val propagateMinConstraints: Boolean,
    @SerialName("content")
    val content: List<String>,
) : Contract
