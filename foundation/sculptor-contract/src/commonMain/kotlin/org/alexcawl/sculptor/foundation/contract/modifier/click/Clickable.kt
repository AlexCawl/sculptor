package org.alexcawl.sculptor.foundation.contract.modifier.click

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.foundation.contract.property.Role

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
    val onClick: String? // TODO: Implement Action
) : ModifierContract
