package org.alexcawl.sculptor.foundation.contract.modifier.height

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ModifierContract
import org.alexcawl.sculptor.foundation.contract.property.Alignment

@Serializable
@SerialName("height@wrap_content_height")
data class WrapContentHeight(
    @SerialName("align")
    val align: Alignment.Vertical,
    @SerialName("unbounded")
    val unbounded: Boolean,
) : ModifierContract
