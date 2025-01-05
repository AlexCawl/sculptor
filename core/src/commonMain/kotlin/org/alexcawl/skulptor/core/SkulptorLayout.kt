package org.alexcawl.skulptor.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.modifier.SkulptorModifier
import org.alexcawl.skulptor.core.state.SkulptorState

@Serializable
data class SkulptorLayout(
    @SerialName("id")
    val id: String,
    @SerialName("module")
    val module: String,
    @SerialName("type")
    val type: String,
    @SerialName("modifier")
    val modifier: List<SkulptorModifier>,
    @SerialName("state")
    val state: SkulptorState,
)
