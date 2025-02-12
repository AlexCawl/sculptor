package org.alexcawl.sculptor.foundation.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.common.contract.layout.StateContract
import org.alexcawl.sculptor.foundation.contract.property.Alignment

@Serializable
@SerialName("box@contract")
data class BoxContract(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("state")
    override val state: Int?,
    @SerialName("modifiers")
    override val modifiers: List<ModifierContract>,
    @SerialName("states")
    override val states: List<BoxStateContract>,
) : LayoutContract

@Serializable
@SerialName("box@state")
data class BoxStateContract(
    override val id: Identifier,
    @SerialName("modifiers")
    override val modifiers: List<ModifierContract>,
    @SerialName("content_alignment")
    val contentAlignment: Alignment,
    @SerialName("propagate_min_constraints")
    val propagateMinConstraints: Boolean,
    @SerialName("content")
    val content: List<String>,
) : StateContract
