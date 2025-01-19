package org.alexcawl.skulptor.modifier

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.skulptor.core.BaseAction
import org.alexcawl.skulptor.core.BaseModifier
import org.alexcawl.skulptor.provider.RoleProvider

@Serializable
sealed class ClickModifier : BaseModifier() {
    @Serializable
    @SerialName("modifier@clickable")
    data class Clickable(
        @SerialName("enabled")
        val enabled: Boolean,
        @SerialName("role")
        val role: RoleProvider,
        @SerialName("on_click_label")
        val onClickLabel: String? = null,
        @SerialName("on_click")
        val onClick: BaseAction
    ) : ClickModifier() {
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.clickable(
                enabled = enabled,
                onClickLabel = onClickLabel,
                role = role(),
                onClick = {
                    dispatch(onClick)
                }
            )
    }

    @Serializable
    @SerialName("modifier@combined_clickable")
    data class CombinedClickable(
        @SerialName("enabled")
        val enabled: Boolean,
        @SerialName("role")
        val role: RoleProvider,
        @SerialName("on_click_label")
        val onClickLabel: String?,
        @SerialName("on_click")
        val onClick: BaseAction,
        @SerialName("on_long_click_label")
        val onLongClickLabel: String? = null,
        @SerialName("on_long_click")
        val onLongClick: BaseAction? = null,
        @SerialName("on_double_click")
        val onDoubleClick: BaseAction? = null,
    ) : ClickModifier() {
        @OptIn(ExperimentalFoundationApi::class)
        override fun Scope.chain(initial: Modifier): Modifier =
            initial.combinedClickable(
                enabled = enabled,
                onClickLabel = onClickLabel,
                role = role(),
                onLongClickLabel = onLongClickLabel,
                onLongClick = {
                    if (onLongClick != null) {
                        dispatch(onLongClick)
                    }
                },
                onDoubleClick = {
                    if (onDoubleClick != null) {
                        dispatch(onDoubleClick)
                    }
                },
                onClick = {
                    dispatch(onClick)
                }
            )
    }
}
