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

typealias AlignmentSerializable = @Serializable(with = AlignmentSerializer::class) Alignment

internal class AlignmentSerializer : KSerializer<Alignment> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "androidx.compose.ui.Alignment",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): Alignment {
        return when (val string = decoder.decodeString()) {
            TopStart -> Alignment.TopStart
            TopCenter -> Alignment.TopCenter
            TopEnd -> Alignment.TopEnd
            CenterStart -> Alignment.CenterStart
            Center -> Alignment.Center
            CenterEnd -> Alignment.CenterEnd
            BottomStart -> Alignment.BottomStart
            BottomCenter -> Alignment.BottomCenter
            BottomEnd -> Alignment.BottomEnd
            else -> throw SerializationException("This Alignment type is not supported: $string")
        }
    }

    override fun serialize(encoder: Encoder, value: Alignment) {
        val string = when (value) {
            Alignment.TopStart -> TopStart
            Alignment.TopCenter -> TopCenter
            Alignment.TopEnd -> TopEnd
            Alignment.CenterStart -> CenterStart
            Alignment.Center -> Center
            Alignment.CenterEnd -> CenterEnd
            Alignment.BottomStart -> BottomStart
            Alignment.BottomCenter -> BottomCenter
            Alignment.BottomEnd -> BottomEnd
            else -> throw SerializationException("This Alignment type is not supported: $value")
        }
        encoder.encodeString(string)
    }

    private companion object Wrapper {
        const val TopStart = "top_start"
        const val TopCenter = "top_center"
        const val TopEnd = "top_end"
        const val CenterStart = "center_start"
        const val Center = "center"
        const val CenterEnd = "center_end"
        const val BottomStart = "bottom_start"
        const val BottomCenter = "bottom_center"
        const val BottomEnd = "bottom_end"
    }
}
