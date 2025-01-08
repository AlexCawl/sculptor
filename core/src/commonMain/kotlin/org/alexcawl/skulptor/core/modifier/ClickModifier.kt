package org.alexcawl.skulptor.core.modifier

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.Skulptor
import org.alexcawl.skulptor.core.SkulptorAction
import org.alexcawl.skulptor.core.SkulptorModifier
import org.alexcawl.skulptor.core.provider.RoleSerializable

@Serializable
sealed interface ClickModifier : SkulptorModifier {
    /**
     * Configure component to receive clicks via input or accessibility "click" event.
     */
    @Serializable
    @SerialName("modifier@clickable")
    data class Clickable(
        @SerialName("enabled")
        val enabled: Boolean,
        @SerialName("role")
        val role: RoleSerializable,
        @SerialName("on_click_label")
        val onClickLabel: String? = null,
        @SerialName("on_click")
        val onClick: SkulptorAction
    ) : ClickModifier {
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.clickable(
                enabled = enabled,
                onClickLabel = onClickLabel,
                role = role,
                onClick = {
                    skulptor.dispatch(onClick)
                }
            )
    }

    /**
     * Configure component to receive clicks, double clicks and long clicks via input or accessibility
     * "click" event.
     */
    @Serializable
    @SerialName("modifier@combined_clickable")
    data class CombinedClickable(
        @SerialName("enabled")
        val enabled: Boolean,
        @SerialName("role")
        val role: RoleSerializable,
        @SerialName("on_click_label")
        val onClickLabel: String?,
        @SerialName("on_click")
        val onClick: SkulptorAction,
        @SerialName("on_long_click_label")
        val onLongClickLabel: String? = null,
        @SerialName("on_long_click")
        val onLongClick: SkulptorAction? = null,
        @SerialName("on_double_click")
        val onDoubleClick: SkulptorAction? = null,
    ) : ClickModifier {
        @OptIn(ExperimentalFoundationApi::class)
        override fun chain(initial: Modifier, skulptor: Skulptor, scope: Any): Modifier =
            initial.combinedClickable(
                enabled = enabled,
                onClickLabel = onClickLabel,
                role = role,
                onLongClickLabel = onLongClickLabel,
                onLongClick = {
                    if (onLongClick != null) {
                        skulptor.dispatch(onLongClick)
                    }
                },
                onDoubleClick = {
                    if (onDoubleClick != null) {
                        skulptor.dispatch(onDoubleClick)
                    }
                },
                onClick = {
                    skulptor.dispatch(onClick)
                }
            )
    }
}
