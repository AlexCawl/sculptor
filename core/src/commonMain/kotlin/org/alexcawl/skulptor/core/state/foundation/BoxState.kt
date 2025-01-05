package org.alexcawl.skulptor.core.state.foundation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorLayout
import org.alexcawl.skulptor.core.state.SkulptorState

@Serializable
@SerialName("state@box")
data class BoxState(
    @SerialName("content")
    val content: SkulptorLayout? = null
) : SkulptorState
