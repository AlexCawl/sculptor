package org.alexcawl.sculptor.contract.foundation.modifier.click

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseAction
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Role

@Serializable
@SerialName("modifier@clickable")
data class Clickable(
    @SerialName("enabled")
    val enabled: Boolean,
    @SerialName("role")
    val role: Role,
    @SerialName("on_click_label")
    val onClickLabel: String?,
    @SerialName("on_click")
    val onClick: BaseAction
) : BaseModifier
