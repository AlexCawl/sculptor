package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ColorSerializer::class)
@JvmInline
public value class Color(public val value: ULong) {
    public constructor(value: String) : this(
        value = factories.find { it.match(value) }
            ?.create(value)
            ?: error("Invalid color: $value")
    )
}

private interface ColorFactory {
    fun match(value: String): Boolean
    fun create(value: String): ULong
}

private val factories: List<ColorFactory> = listOf(
    object : ColorFactory {
        override fun match(value: String): Boolean =
            value.matches(Regex(pattern = "^[0-9a-fA-F]{6}$"))

        override fun create(value: String): ULong = value.toULong(radix = 16) xor 0xFF000000UL
    },
    object : ColorFactory {
        override fun match(value: String): Boolean =
            value.matches(Regex(pattern = "^[0-9a-fA-F]{8}$"))

        override fun create(value: String): ULong = value.toULong(radix = 16)
    },
)

internal class ColorSerializer : KSerializer<Color> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
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
