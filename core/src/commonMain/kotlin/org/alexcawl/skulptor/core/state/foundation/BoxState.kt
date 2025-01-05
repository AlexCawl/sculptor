package org.alexcawl.skulptor.core.state.foundation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SLayout
import org.alexcawl.skulptor.core.alignment.SAlignment
import org.alexcawl.skulptor.core.state.SkulptorState

@Serializable
@SerialName("state@box")
data class BoxState(
    @SerialName("content_alignment")
    val contentAlignment: SAlignment? = null,

    @SerialName("propagate_min_constraints")
    val propagateMinConstraints: Boolean? = null,

    @SerialName("content")
    val content: SLayout? = null
) : SkulptorState
