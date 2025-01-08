package org.alexcawl.skulptor.core.provider

import androidx.compose.ui.unit.Dp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.Provider

@Serializable
sealed interface DpProvider : Provider<Dp> {
    @Serializable
    @SerialName("number")
    data class Number(val value: Float) : DpProvider {
        override fun invoke(): Dp = Dp(value)
    }

    @Serializable
    @SerialName("hairline")
    data object Hairline : DpProvider {
        override fun invoke(): Dp = Dp.Hairline
    }

    @Serializable
    @SerialName("infinity")
    data object Infinity : DpProvider {
        override fun invoke(): Dp = Dp.Infinity
    }

    @Serializable
    @SerialName("unspecified")
    data object Unspecified : DpProvider {
        override fun invoke(): Dp = Dp.Unspecified
    }
}
