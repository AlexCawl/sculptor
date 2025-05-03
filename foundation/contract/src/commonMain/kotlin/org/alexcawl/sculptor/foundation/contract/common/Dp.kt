package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = DpSerializer::class)
public sealed interface Dp {
    public data class Number(val value: Float) : Dp

    public data object Hairline : Dp

    public data object Infinity : Dp

    public data object Unspecified : Dp
}

public val Number.dp: Dp
    get() = Dp.Number(value = this.toFloat())


internal class DpSerializer : KSerializer<Dp> {
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
