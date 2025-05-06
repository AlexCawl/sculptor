package org.alexcawl.sculptor.core.contract

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Represents a unique identifier within the system.
 *
 * This value class encapsulates a string value that serves as an identifier.
 * It supports concatenation with other [Identifier] instances or strings using the `+` operator,
 * which appends the values with an '@' separator.
 *
 * @property value The string representation of the identifier.
 */
@JvmInline
@Serializable
public value class Identifier(public val value: String) {

    /**
     * Concatenates this [Identifier] with another [Identifier].
     *
     * The resulting [Identifier] is a combination of the two values, separated by '@'.
     *
     * @param other The [Identifier] to concatenate with this one.
     * @return A new [Identifier] with the concatenated value.
     */
    public operator fun plus(other: Identifier): Identifier =
        Identifier(value = "${value}@${other.value}")

    /**
     * Concatenates this [Identifier] with a [String].
     *
     * The resulting [Identifier] is a combination of the current value and the provided string, separated by '@'.
     *
     * @param other The [String] to concatenate with this [Identifier].
     * @return A new [Identifier] with the concatenated value.
     */
    public operator fun plus(other: String): Identifier =
        Identifier(value = "${value}@${other}")

    /**
     * Returns the string representation of this [Identifier].
     *
     * @return The string value of the identifier.
     */
    override fun toString(): String = value
}
