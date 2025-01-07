package org.alexcawl.skulptor.core.attribute

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.alexcawl.skulptor.core.SkulptorAttribute

typealias ColorSerializable = @Serializable(with = ColorSerializer::class) Color

internal class ColorSerializer : KSerializer<Color> {
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
