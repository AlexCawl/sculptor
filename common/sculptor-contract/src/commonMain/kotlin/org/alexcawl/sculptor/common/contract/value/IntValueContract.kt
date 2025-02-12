package org.alexcawl.sculptor.common.contract.value

import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier

@Serializable
class IntValueContract(
    override val id: Identifier,
    override val value: Int,
) : NumberValueContract()
