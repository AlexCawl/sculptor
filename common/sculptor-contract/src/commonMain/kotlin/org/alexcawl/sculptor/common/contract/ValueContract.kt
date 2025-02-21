package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName

/**
 * TODO: docs
 */
public interface ValueContract {
    @SerialName("id")
    public val id: Identifier
}
