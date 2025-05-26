package org.alexcawl.sculptor.foundation.contract.layout

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.Identifier
import org.alexcawl.sculptor.core.contract.StateContract
import org.alexcawl.sculptor.foundation.contract.common.Alignment

@Serializable
@SerialName("box@state")
public data class BoxState(
    @SerialName("content_alignment")
    val contentAlignment: Alignment = Alignment.TopStart,
    @SerialName("propagate_min_constraints")
    val propagateMinConstraints: Boolean = false,
    @SerialName("content")
    val content: List<Identifier> = emptyList(),
) : StateContract
