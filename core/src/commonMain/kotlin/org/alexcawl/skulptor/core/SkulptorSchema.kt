package org.alexcawl.skulptor.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.factory.BaseLayout
import org.alexcawl.skulptor.core.state.BaseState

@Serializable
data class SkulptorSchema(
    @SerialName("layouts")
    val layouts: List<BaseLayout<out BaseState>>,
    @SerialName("states")
    val states: List<BaseState>,
)
