package org.alexcawl.sculptor.common.contract.value

import kotlinx.serialization.Serializable
import org.alexcawl.sculptor.common.contract.Identifier

/**
 * A contract for a value. Value is a token that makes sense to business logic of the screen.
 */
@Serializable
sealed interface ValueContract {
    val id: Identifier
}
