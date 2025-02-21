package org.alexcawl.sculptor.common.contract.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mock(
    @SerialName("data")
    val data: String,
)
