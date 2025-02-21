package org.alexcawl.sculptor.foundation.contract.value

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier

/**
 * TODO: docs
 */
@Serializable
public data class DoubleValueContract(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("value")
    override val value: Double
) : NumberValueContract()
