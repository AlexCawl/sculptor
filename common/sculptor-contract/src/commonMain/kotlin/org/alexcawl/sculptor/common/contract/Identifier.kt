package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.Serializable

/**
 * A unique identifier.
 */
@JvmInline
@Serializable
value class Identifier(val id: String)

val String.id: Identifier
    get() = Identifier(id = this)

fun combineIds(id1: Identifier, id2: Identifier): String = "${id1.id}@${id2.id}"
