package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.Serializable

/**
 * TODO: docs
 */
@JvmInline
@Serializable
public value class Identifier(
    public val value: String,
) {
    /**
     * TODO: docs
     */
    public operator fun plus(other: Identifier): String = "${value}@${other.value}"
}

/**
 * TODO: docs
 */
public val String.id: Identifier
    get() = Identifier(value = this)
