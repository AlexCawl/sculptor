package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.Identifier
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.foundation.contract.common.Alignment

@Serializable
@SerialName("box@state")
public data class BoxState(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("content_alignment")
    val contentAlignment: Alignment,
    @SerialName("propagate_min_constraints")
    val propagateMinConstraints: Boolean,
    @SerialName("content")
    val content: List<Identifier>,
) : StateContract
