package org.alexcawl.sculptor.common.contract.value

import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier

@Serializable
data class BooleanValueContract(
    override val id: Identifier,
    val value: Boolean,
) : ValueContract
