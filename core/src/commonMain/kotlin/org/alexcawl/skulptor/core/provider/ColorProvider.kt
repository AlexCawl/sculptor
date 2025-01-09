package org.alexcawl.skulptor.core.provider

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.alexcawl.skulptor.core.Provider

@Serializable(with = ColorSerializer::class)
data class ColorProvider(private val color: Color) : Provider<Color> {
    override fun invoke(): Color = color
}

private class ColorSerializer : KSerializer<ColorProvider> {
    override val descriptor: SerialDescriptor =  PrimitiveSerialDescriptor(
        serialName = "androidx.compose.ui.graphics.Color",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): ColorProvider {
        val string = decoder.decodeString()
        return ColorProvider(Color(value = string.toULong(radix = 16)))
    }

    override fun serialize(encoder: Encoder, value: ColorProvider) {
        val string = value().value.toString(radix = 16)
        encoder.encodeString(string)
    }
}
