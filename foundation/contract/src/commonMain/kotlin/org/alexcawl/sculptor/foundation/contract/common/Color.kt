package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

private const val RGB_REGEX = "^[0-9a-fA-F]{6}$"
private const val RGBA_REGEX = "^[0-9a-fA-F]{8}$"
private const val UNSPECIFIED_COLOR = "unspecified"

@Serializable(with = ColorSerializer::class)
public sealed interface Color {
    public val content: String

    @Serializable
    public data class RGB(override val content: String) : Color {
        init {
            check(value = content.matches(Regex(pattern = RGB_REGEX)))
        }
    }

    @Serializable
    public data class RGBA(override val content: String) : Color {
        init {
            check(value = content.matches(Regex(pattern = RGBA_REGEX)))
        }
    }

    @Serializable
    public data object Unspecified : Color {
        override val content: String = "unspecified"
    }

    public val r: Int
        get() = content.substring(0, 2).toInt(radix = 16)
    public val g: Int
        get() = content.substring(2, 4).toInt(radix = 16)
    public val b: Int
        get() = content.substring(4, 6).toInt(radix = 16)
    public val a: Int
        get() = when (this) {
            is RGB -> 0xFF
            is RGBA -> content.substring(6, 8).toInt(radix = 16)
            else -> error("Invalid color: $content")
        }

    public companion object {
        public operator fun invoke(content: String): Color = when {
            content.matches(Regex(pattern = RGB_REGEX)) -> RGB(content)
            content.matches(Regex(pattern = RGBA_REGEX)) -> RGBA(content)
            content.matches(Regex(pattern = UNSPECIFIED_COLOR)) -> Unspecified
            else -> error("Invalid color: $content")
        }
    }
}

internal class ColorSerializer : KSerializer<Color> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "androidx.compose.ui.graphics.Color",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): Color {
        val string = decoder.decodeString()
        return when {
            string.matches(Regex(pattern = RGB_REGEX)) -> Color.RGB(content = string)
            string.matches(Regex(pattern = RGBA_REGEX)) -> Color.RGBA(content = string)
            string.matches(Regex(pattern = UNSPECIFIED_COLOR)) -> Color.Unspecified
            else -> error("Invalid color: $string")
        }
    }

    override fun serialize(encoder: Encoder, value: Color) {
        val string = value.content
        encoder.encodeString(string)
    }
}
