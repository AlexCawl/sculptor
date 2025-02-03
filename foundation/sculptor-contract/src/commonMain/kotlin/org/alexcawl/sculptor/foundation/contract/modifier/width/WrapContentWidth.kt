package org.alexcawl.sculptor.foundation.contract.modifier.width

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ContractModifier
import org.alexcawl.sculptor.foundation.contract.property.Alignment

@Serializable
@SerialName("modifier@wrap_content_width")
data class WrapContentWidth(
    @SerialName("align")
    val align: Alignment.Horizontal,
    @SerialName("unbounded")
    val unbounded: Boolean,
) : ContractModifier
