package org.alexcawl.sculptor.contract.foundation.modifier.height

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Alignment

@Serializable
@SerialName("height@wrap_content_height")
data class WrapContentHeight(
    @SerialName("align")
    val align: Alignment.Vertical,
    @SerialName("unbounded")
    val unbounded: Boolean,
) : BaseModifier
