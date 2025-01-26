package org.alexcawl.sculptor.contract.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface DpSize {
    val width: Dp
    val height: Dp

    @Serializable
    data object Zero : DpSize {
        override val width: Dp = 0.dp
        override val height: Dp = 0.dp
    }

    @Serializable
    data object Unspecified : DpSize {
        override val width: Dp = Dp.Unspecified
        override val height: Dp = Dp.Unspecified
    }

    @Serializable
    data class Content(
        @SerialName("width")
        override val width: Dp,
        @SerialName("height")
        override val height: Dp,
    ) : DpSize
}
