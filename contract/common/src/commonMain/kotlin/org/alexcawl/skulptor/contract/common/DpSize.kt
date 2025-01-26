package org.alexcawl.skulptor.contract.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface DpSize {
    @Serializable
    data object Zero : DpSize

    @Serializable
    data object Unspecified : DpSize

    @Serializable
    data class Content(
        @SerialName("width")
        val width: Dp,
        @SerialName("height")
        val height: Dp,
    ) : DpSize
}
