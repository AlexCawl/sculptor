package org.alexcawl.sculptor.contract.foundation.modifier.size

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Dp

@Serializable
@SerialName("size@required_size_in")
data class RequiredSizeIn(
    @SerialName("min")
    val min: Dp,
    @SerialName("max")
    val max: Dp,
) : BaseModifier
