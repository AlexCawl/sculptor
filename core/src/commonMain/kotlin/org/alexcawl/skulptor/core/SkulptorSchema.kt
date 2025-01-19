package org.alexcawl.skulptor.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkulptorSchema(
    @SerialName("layouts")
    val layouts: List<BaseLayout<*>>,
    @SerialName("states")
    val states: List<BaseState>,
)
