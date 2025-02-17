package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.Serializable

/**
 * TODO: docs
 */
@JvmInline
@Serializable
public value class Identifier(
    public val id: String,
)

/**
 * TODO: docs
 */
public val String.id: Identifier
    get() = Identifier(id = this)

/**
 * TODO: docs
 */
public fun combineIds(id1: Identifier, id2: Identifier): String = "${id1.id}@${id2.id}"
