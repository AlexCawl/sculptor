package org.alexcawl.sculptor.foundation.contract.modifier.height

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.layout.ModifierContract
import org.alexcawl.sculptor.foundation.contract.property.Dp

@Serializable
@SerialName("height@required_height_in")
data class RequiredHeightIn(
    @SerialName("min")
    val min: Dp,
    @SerialName("max")
    val max: Dp,
) : ModifierContract
