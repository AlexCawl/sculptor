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

@Serializable(with = ColorWrapperSerializer::class)
data class ColorWrapper(
    @SerialName("argb")
    val argb: Long
) : SkulptorAttribute<Color> {
    override fun asCompose(): Color =
        Color(color = argb)
}

private class ColorWrapperSerializer : KSerializer<ColorWrapper> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "org.alexcawl.skulptor.core.attribute.ColorWrapper",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): ColorWrapper {
        val string = decoder.decodeString()
        return ColorWrapper(string.toLong(radix = 16))
    }

    override fun serialize(encoder: Encoder, value: ColorWrapper) {
        val string = value.argb.toString(radix = 16)
        encoder.encodeString(string)
    }
}
