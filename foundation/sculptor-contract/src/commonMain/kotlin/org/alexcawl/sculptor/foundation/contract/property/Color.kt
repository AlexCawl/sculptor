package org.alexcawl.sculptor.foundation.contract.property

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ColorSerializer::class)
data class Color(val value: ULong) {
    constructor(argb: String) : this(argb.toULong(radix = 16))
}

private class ColorSerializer : KSerializer<Color> {
    override val descriptor: SerialDescriptor =  PrimitiveSerialDescriptor(
        serialName = "androidx.compose.ui.graphics.Color",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): Color {
        val string = decoder.decodeString()
        return Color(value = string.toULong(radix = 16))
    }

    override fun serialize(encoder: Encoder, value: Color) {
        val string = value.value.toString(radix = 16)
        encoder.encodeString(string)
    }
}
