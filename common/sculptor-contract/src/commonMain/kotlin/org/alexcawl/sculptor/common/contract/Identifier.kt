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

fun combineIds(id1: Identifier, id2: Identifier): String = "${id1.identifier}@${id2.identifier}"
