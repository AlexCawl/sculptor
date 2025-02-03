package org.alexcawl.sculptor.foundation.contract.property

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = DpSerializer::class)
sealed interface Dp {
    data class Number(val value: Float) : Dp

    data object Hairline : Dp

    data object Infinity : Dp

    data object Unspecified : Dp
}

private class DpSerializer : KSerializer<Dp> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "Dp",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): Dp {
        return when (val string = decoder.decodeString()) {
            HAIRLINE -> Dp.Hairline
            INFINITY -> Dp.Infinity
            UNSPECIFIED -> Dp.Unspecified
            else -> Dp.Number(value = string.toFloat())
        }
    }

    override fun serialize(encoder: Encoder, value: Dp) {
        val string = when (value) {
            is Dp.Hairline -> HAIRLINE
            is Dp.Infinity -> INFINITY
            is Dp.Unspecified -> UNSPECIFIED
            is Dp.Number -> value.value.toString()
        }
        encoder.encodeString(string)
    }

    private companion object {
        const val HAIRLINE = "hairline"
        const val INFINITY = "infinity"
        const val UNSPECIFIED = "unspecified"
    }
}
