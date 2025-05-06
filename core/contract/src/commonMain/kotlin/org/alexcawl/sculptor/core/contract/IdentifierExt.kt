package org.alexcawl.sculptor.core.contract

/**
 * Extension property for [String] that converts it to an [Identifier].
 *
 * @return An [Identifier] instance created from the string value.
 */
public val String.id: Identifier
    get() = Identifier(value = this)
