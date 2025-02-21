package org.alexcawl.sculptor.foundation.contract.value

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.ValueContract

/**
 * TODO: docs
 */
@Serializable
public abstract class NumberValueContract : ValueContract {
    @SerialName("value")
    public abstract val value: Number
}
