package org.alexcawl.sculptor.common.contract.value

import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier

@Serializable
data class DoubleValueContract(
    override val id: Identifier,
    override val value: Double,
) : NumberValueContract()
