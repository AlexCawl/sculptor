package org.alexcawl.skulptor.core.provider

import androidx.compose.ui.unit.DpSize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.common.Provider

@Serializable
sealed interface DpSizeProvider : Provider<DpSize> {
    @Serializable
    @SerialName("zero")
    data object Zero : DpSizeProvider {
        override fun invoke(): DpSize = DpSize.Zero
    }

    @Serializable
    @SerialName("unspecified")
    data object Unspecified : DpSizeProvider {
        override fun invoke(): DpSize = DpSize.Unspecified
    }

    @Serializable
    @SerialName("content")
    data class Content(
        val width: DpProvider,
        val height: DpProvider,
    ) : Provider<DpSize> {
        override fun invoke(): DpSize = DpSize(width = width(), height = height())
    }
}
