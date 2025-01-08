@file:Suppress("ConstPropertyName")

package org.alexcawl.skulptor.core.provider.alignment

import androidx.compose.ui.Alignment
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

typealias AlignmentVerticalSerializable = @Serializable(with = AlignmentVerticalSerializer::class) Alignment.Vertical

internal class AlignmentVerticalSerializer : KSerializer<Alignment.Vertical> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "androidx.compose.ui.Alignment.Vertical",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): Alignment.Vertical {
        return when (val string = decoder.decodeString()) {
            Top -> Alignment.Top
            CenterVertically -> Alignment.CenterVertically
            Bottom -> Alignment.Bottom
            else -> throw SerializationException("This Alignment.Vertical type is not supported: $string")
        }
    }

    override fun serialize(encoder: Encoder, value: Alignment.Vertical) {
        val string = when (value) {
            Alignment.Top -> Top
            Alignment.CenterVertically -> CenterVertically
            Alignment.Bottom -> Bottom
            else -> throw SerializationException("This Alignment.Vertical type is not supported: $value")
        }
        encoder.encodeString(string)
    }

    private companion object Wrapper {
        const val Top = "top"
        const val CenterVertically = "center_vertically"
        const val Bottom = "bottom"
    }
}
