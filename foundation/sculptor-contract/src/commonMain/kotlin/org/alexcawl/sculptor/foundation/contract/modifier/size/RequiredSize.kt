package org.alexcawl.sculptor.foundation.contract.modifier.size

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.foundation.contract.property.Dp

@Serializable
@SerialName("size@required_size")
data class RequiredSize(
    @SerialName("size")
    val size: Dp,
) : ModifierContract
