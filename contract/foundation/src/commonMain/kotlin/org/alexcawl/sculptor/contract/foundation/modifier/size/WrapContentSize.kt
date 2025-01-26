package org.alexcawl.sculptor.contract.foundation.modifier.size

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Alignment

@Serializable
@SerialName("size@wrap_content_size")
data class WrapContentSize(
    @SerialName("align")
    val align: Alignment.Both,
    @SerialName("unbounded")
    val unbounded: Boolean,
) : BaseModifier
