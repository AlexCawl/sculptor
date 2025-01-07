package org.alexcawl.skulptor.core.modifier.clickable

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.SkulptorAction

@Serializable
sealed interface ClickAction : SkulptorAction {
    @Serializable
    @SerialName("action@click")
    data class Click(
        override val id: String
    ) : ClickAction

    @Serializable
    @SerialName("action@long_click")
    data class LongClick(
        override val id: String
    ) : ClickAction

    @Serializable
    @SerialName("action@double_click")
    data class DoubleClick(
        override val id: String
    ) : ClickAction
}
