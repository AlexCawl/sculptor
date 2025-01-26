package org.alexcawl.sculptor.contract.foundation.modifier.width

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Dp

@Serializable
@SerialName("modifier@width")
data class Width(
    @SerialName("width")
    val width: Dp
) : BaseModifier