package org.alexcawl.sculptor.contract.foundation.modifier.size

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.BaseModifier
import org.alexcawl.sculptor.contract.common.Dp

@Serializable
@SerialName("size@required_size")
data class RequiredSize(
    @SerialName("size")
    val size: Dp,
) : BaseModifier
