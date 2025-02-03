package org.alexcawl.sculptor.foundation.contract.modifier.size

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ContractModifier
import org.alexcawl.sculptor.foundation.contract.property.Alignment

@Serializable
@SerialName("size@wrap_content_size")
data class WrapContentSize(
    @SerialName("align")
    val align: Alignment,
    @SerialName("unbounded")
    val unbounded: Boolean,
) : ContractModifier
