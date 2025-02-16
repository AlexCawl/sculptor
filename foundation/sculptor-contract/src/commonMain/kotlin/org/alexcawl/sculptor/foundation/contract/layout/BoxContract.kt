package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.foundation.contract.property.Alignment

@Serializable
@SerialName("box@layout")
data class BoxLayoutContract(
    override val id: Identifier,
    override val state: Identifier,
    override val modifiers: List<ModifierContract>,
    override val states: List<BoxStateContract>
) : LayoutContract

@Serializable
@SerialName("box@state")
data class BoxStateContract(
    override val id: Identifier,
    override val modifiers: List<ModifierContract>,
    @SerialName("content_alignment")
    val contentAlignment: Alignment,
    @SerialName("propagate_min_constraints")
    val propagateMinConstraints: Boolean,
    @SerialName("content")
    val content: List<Identifier>,
) : StateContract
