package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
public value class Identifier(public val value: String) {
    public operator fun plus(other: Identifier): Identifier =
        Identifier(value = "${value}@${other.value}")

    public operator fun plus(other: String): Identifier =
        Identifier(value = "${value}@${other}")

    override fun toString(): String = value
}

public val String.id: Identifier
    get() = Identifier(value = this)
