package org.alexcawl.sculptor.contract.foundation.modifier.click

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseAction
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Role

@Serializable
@SerialName("modifier@combined_clickable")
data class CombinedClickable(
    @SerialName("enabled")
    val enabled: Boolean,
    @SerialName("role")
    val role: Role,
    @SerialName("on_click_label")
    val onClickLabel: String?,
    @SerialName("on_click")
    val onClick: BaseAction,
    @SerialName("on_long_click_label")
    val onLongClickLabel: String?,
    @SerialName("on_long_click")
    val onLongClick: BaseAction?,
    @SerialName("on_double_click")
    val onDoubleClick: BaseAction?,
) : BaseModifier
