@file:Suppress("ConstPropertyName")

package org.alexcawl.skulptor.core.attribute.alignment

import androidx.compose.ui.Alignment
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

typealias AlignmentHorizontalSerializable = @Serializable(with = AlignmentHorizontalSerializer::class) Alignment.Horizontal

internal class AlignmentHorizontalSerializer : KSerializer<Alignment.Horizontal> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "androidx.compose.ui.Alignment.Horizontal",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): Alignment.Horizontal {
        return when (val string = decoder.decodeString()) {
            Start -> Alignment.Start
            CenterHorizontally -> Alignment.CenterHorizontally
            End -> Alignment.End
            else -> throw SerializationException("This Alignment.Horizontal type is not supported: $string")
        }
    }

    override fun serialize(encoder: Encoder, value: Alignment.Horizontal) {
        val string = when (value) {
            Alignment.Start -> Start
            Alignment.CenterHorizontally -> CenterHorizontally
            Alignment.End -> End
            else -> throw SerializationException("This Alignment.Horizontal type is not supported: $value")
        }
        encoder.encodeString(string)
    }

    private companion object Wrapper {
        const val Start = "start"
        const val CenterHorizontally = "center_horizontally"
        const val End = "end"
    }
}
