package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public sealed interface DpSize {
    public val width: Dp
    public val height: Dp

    @Serializable
    public data object Zero : DpSize {
        override val width: Dp
            get() = 0.dp
        override val height: Dp
            get() = 0.dp
    }

    @Serializable
    public data object Unspecified : DpSize {
        override val width: Dp
            get() = Dp.Unspecified
        override val height: Dp
            get() = Dp.Unspecified
    }

    @Serializable
    public data class Content(
        @SerialName("width")
        override val width: Dp,
        @SerialName("height")
        override val height: Dp,
    ) : DpSize
}
