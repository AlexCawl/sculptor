package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.Serializable

/**
 * A contract for a value. Value is a token that makes sense to business logic of the screen.
 */
@Serializable
sealed interface ValueContract {
    val id: Identifier
}

@Serializable
data class StringValueContract(override val id: Identifier, val value: String) : ValueContract

@Serializable
data class BooleanValueContract(override val id: Identifier, val value: Boolean) : ValueContract

@Serializable
abstract class NumberValueContract : ValueContract {
    abstract val value: Number
}

@Serializable
data class FloatValueContract(override val id: Identifier, override val value: Float) : NumberValueContract()

@Serializable
data class DoubleValueContract(override val id: Identifier, override val value: Double) : NumberValueContract()

@Serializable
class IntValueContract(override val id: Identifier, override val value: Int) : NumberValueContract()

@Serializable
data class LongValueContract(override val id: Identifier, override val value: Long) : NumberValueContract()
