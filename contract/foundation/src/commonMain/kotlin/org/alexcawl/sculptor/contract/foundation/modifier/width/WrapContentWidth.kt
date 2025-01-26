package org.alexcawl.sculptor.contract.foundation.modifier.width

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Alignment

@Serializable
@SerialName("modifier@wrap_content_width")
data class WrapContentWidth(
    @SerialName("align")
    val align: Alignment.Horizontal,
    @SerialName("unbounded")
    val unbounded: Boolean,
) : BaseModifier
