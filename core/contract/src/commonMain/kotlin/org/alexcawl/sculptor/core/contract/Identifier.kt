package org.alexcawl.sculptor.core.contract

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@JvmInline
@Serializable
public value class Identifier(public val value: String) {
    public operator fun plus(other: Identifier): Identifier =
        Identifier(value = "${value}@${other.value}")

    public operator fun plus(other: String): Identifier =
        Identifier(value = "${value}@${other}")

    override fun toString(): String = value
}
