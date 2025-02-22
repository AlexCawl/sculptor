package org.alexcawl.sculptor.foundation.contract.value

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.ValueContract

/**
 * TODO: docs
 */
@Serializable
public data class BooleanValueContract(
    @SerialName("id")
    public override val id: Identifier,
    @SerialName("value")
    public override val value: Boolean,
) : ValueContract
