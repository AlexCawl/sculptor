package org.alexcawl.sculptor.foundation.contract.modifier.width

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ContractModifier
import org.alexcawl.sculptor.foundation.contract.property.Dp

@Serializable
@SerialName("modifier@required_width")
data class RequiredWidth(
    @SerialName("width")
    val width: Dp,
) : ContractModifier
