package org.alexcawl.sculptor.foundation.contract.modifier

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.core.contract.ModifierContract
import org.alexcawl.sculptor.foundation.contract.common.Role

@Serializable
@SerialName("modifier@clickable")
public data class Clickable(
    @SerialName("enabled")
    val enabled: Boolean,
    @SerialName("role")
    val role: Role,
    @SerialName("on_click_label")
    val onClickLabel: String?,
    @SerialName("on_click")
    val onClick: String? // TODO: Implement Action
) : ModifierContract

@Serializable
@SerialName("modifier@combined_clickable")
public data class CombinedClickable(
    @SerialName("enabled")
    val enabled: Boolean,
    @SerialName("role")
    val role: Role,
    @SerialName("on_click_label")
    val onClickLabel: String?,
    @SerialName("on_click")
    val onClick: String,
    @SerialName("on_long_click_label")
    val onLongClickLabel: String?,
    @SerialName("on_long_click")
    val onLongClick: String?,
    @SerialName("on_double_click")
    val onDoubleClick: String?,
) : ModifierContract
