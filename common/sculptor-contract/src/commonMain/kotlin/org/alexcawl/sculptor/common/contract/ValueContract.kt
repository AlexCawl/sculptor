package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO: docs
 */
public interface ValueContract {
    @SerialName("id")
    public val id: Identifier
}

/**
 * TODO: docs
 */
@Serializable
public data class StringValueContract(
    @SerialName("id")
    public override val id: Identifier,
    @SerialName("value")
    public val value: String,
) : ValueContract

/**
 * TODO: docs
 */
@Serializable
public data class BooleanValueContract(
    @SerialName("id")
    public override val id: Identifier,
    @SerialName("value")
    public val value: Boolean,
) : ValueContract

/**
 * TODO: docs
 */
@Serializable
public abstract class NumberValueContract : ValueContract {
    @SerialName("value")
    public abstract val value: Number
}

/**
 * TODO: docs
 */
@Serializable
public data class FloatValueContract(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("value")
    override val value: Float
) : NumberValueContract()

/**
 * TODO: docs
 */
@Serializable
public data class DoubleValueContract(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("value")
    override val value: Double
) : NumberValueContract()

/**
 * TODO: docs
 */
@Serializable
public class IntValueContract(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("value")
    override val value: Int
) : NumberValueContract()

/**
 * TODO: docs
 */
@Serializable
public data class LongValueContract(
    @SerialName("id")
    override val id: Identifier,
    @SerialName("value")
    override val value: Long
) : NumberValueContract()
