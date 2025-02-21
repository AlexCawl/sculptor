package org.alexcawl.sculptor.common.presenter.mock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mock(
    @SerialName("data")
    val data: String,
)
