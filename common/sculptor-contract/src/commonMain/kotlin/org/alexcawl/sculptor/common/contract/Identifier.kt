package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.Serializable

/**
 * A unique identifier.
 */
@JvmInline
@Serializable
value class Identifier(val identifier: String)

val String.id: Identifier
    get() = Identifier(identifier = this)