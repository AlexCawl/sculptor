package org.alexcawl.sculptor.core.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SculptorContent(
    @SerialName("schema")
    val sculptorScreen: SculptorScreen,
    @SerialName("id")
    val id: Identifier,
    @SerialName("version")
    val version: String,
)
