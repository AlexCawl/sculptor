package org.alexcawl.skulptor.core.modifier.actions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.alexcawl.skulptor.core.SkulptorAction
import org.alexcawl.skulptor.core.modifier.SkulptorModifier
import org.alexcawl.skulptor.core.role.RoleWrapper

internal val clickableSerializersModule = SerializersModule {
    polymorphic(SkulptorModifier::class) {
        subclass(Clickable::class)
        subclass(CombinedClickable::class)
    }
}

/**
 * Configure component to receive clicks via input or accessibility "click" event.
 *
 * @param enabled Controls the enabled state. When `false`, [onClick], and this modifier will
 * appear disabled for accessibility services
 * @param onClickLabel semantic / accessibility label for the [onClick] action
 * @param role the type of user interface element. Accessibility services might use this
 * to describe the element or do customizations
 * @param onClick will be called when user clicks on the element
 */
@Serializable
@SerialName("modifier@clickable")
data class Clickable(
    @SerialName("enabled")
    val enabled: Boolean,

    @SerialName("role")
    val role: RoleWrapper,

    @SerialName("on_click_label")
    val onClickLabel: String? = null,

    @SerialName("on_click")
    val onClick: SkulptorAction
) : SkulptorModifier {
    @Composable
    override fun asCompose(): Modifier =
        Modifier.clickable(
            enabled = enabled,
            onClickLabel = onClickLabel,
            role = role.asCompose(),
            onClick = { /* TODO */ }
        )
}

/**
 * Configure component to receive clicks, double clicks and long clicks via input or accessibility
 * "click" event.
 *
 * @param enabled Controls the enabled state. When `false`, [onClick], [onLongClick] or
 * [onDoubleClick] won't be invoked
 * @param onClickLabel semantic / accessibility label for the [onClick] action
 * @param role the type of user interface element. Accessibility services might use this
 * to describe the element or do customizations
 * @param onLongClickLabel semantic / accessibility label for the [onLongClick] action
 * @param onLongClick will be called when user long presses on the element
 * @param onDoubleClick will be called when user double clicks on the element
 * @param onClick will be called when user clicks on the element
 */
@Serializable
@SerialName("modifier@combined_clickable")
data class CombinedClickable(
    @SerialName("enabled")
    val enabled: Boolean,

    @SerialName("role")
    val role: RoleWrapper,

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
) : SkulptorModifier {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun asCompose(): Modifier =
        Modifier.combinedClickable(
            enabled = enabled,
            onClickLabel = onClickLabel,
            role = role.asCompose(),
            onLongClickLabel = onLongClickLabel,
            onLongClick = { /* TODO */ },
            onDoubleClick = { /* TODO */ },
            onClick = { /* TODO */ }
        )
}
