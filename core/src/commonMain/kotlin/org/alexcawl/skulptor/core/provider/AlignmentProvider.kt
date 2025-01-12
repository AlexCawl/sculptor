@file:Suppress("ConstPropertyName", "ObjectPropertyNaming")

package org.alexcawl.skulptor.core.provider

import androidx.compose.ui.Alignment
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.alexcawl.skulptor.core.common.Provider

object AlignmentProvider {
    @Serializable(with = HorizontalAndVerticalSerializer::class)
    data class HorizontalAndVertical(private val alignment: Alignment) : Provider<Alignment> {
        override fun invoke(): Alignment = alignment
    }

    @Serializable(with = HorizontalSerializer::class)
    data class Horizontal(private val alignment: Alignment.Horizontal) : Provider<Alignment.Horizontal> {
        override fun invoke(): Alignment.Horizontal = alignment
    }

    @Serializable(with = VerticalSerializer::class)
    data class Vertical(private val alignment: Alignment.Vertical) : Provider<Alignment.Vertical> {
        override fun invoke(): Alignment.Vertical = alignment
    }
}

private class HorizontalAndVerticalSerializer :
    KSerializer<AlignmentProvider.HorizontalAndVertical> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "androidx.compose.ui.Alignment",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): AlignmentProvider.HorizontalAndVertical {
        val alignment = when (val string = decoder.decodeString()) {
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
        return AlignmentProvider.HorizontalAndVertical(alignment)
    }


    override fun serialize(encoder: Encoder, value: AlignmentProvider.HorizontalAndVertical) {
        val string = when (val alignment = value()) {
            Alignment.TopStart -> TopStart
            Alignment.TopCenter -> TopCenter
            Alignment.TopEnd -> TopEnd
            Alignment.CenterStart -> CenterStart
            Alignment.Center -> Center
            Alignment.CenterEnd -> CenterEnd
            Alignment.BottomStart -> BottomStart
            Alignment.BottomCenter -> BottomCenter
            Alignment.BottomEnd -> BottomEnd
            else -> throw SerializationException("This Alignment type is not supported: $alignment")
        }
        encoder.encodeString(string)
    }

    private companion object {
        const val TopStart = "TopStart"
        const val TopCenter = "TopCenter"
        const val TopEnd = "TopEnd"
        const val CenterStart = "CenterStart"
        const val Center = "Center"
        const val CenterEnd = "CenterEnd"
        const val BottomStart = "BottomStart"
        const val BottomCenter = "BottomCenter"
        const val BottomEnd = "BottomEnd"
    }
}

private class HorizontalSerializer : KSerializer<AlignmentProvider.Horizontal> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "androidx.compose.ui.Alignment.Horizontal",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): AlignmentProvider.Horizontal {
        val alignment = when (val string = decoder.decodeString()) {
            Start -> Alignment.Start
            CenterHorizontally -> Alignment.CenterHorizontally
            End -> Alignment.End
            else -> throw SerializationException("This Alignment.Horizontal type is not supported: $string")
        }
        return AlignmentProvider.Horizontal(alignment)
    }

    override fun serialize(encoder: Encoder, value: AlignmentProvider.Horizontal) {
        val string = when (val alignment = value()) {
            Alignment.Start -> Start
            Alignment.CenterHorizontally -> CenterHorizontally
            Alignment.End -> End
            else -> throw SerializationException("This Alignment.Horizontal type is not supported: $alignment")
        }
        encoder.encodeString(string)
    }

    private companion object {
        const val Start = "Start"
        const val CenterHorizontally = "CenterHorizontally"
        const val End = "End"
    }
}

private class VerticalSerializer : KSerializer<AlignmentProvider.Vertical> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "androidx.compose.ui.Alignment.Vertical",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): AlignmentProvider.Vertical {
        val alignment = when (val string = decoder.decodeString()) {
            Top -> Alignment.Top
            CenterVertically -> Alignment.CenterVertically
            Bottom -> Alignment.Bottom
            else -> throw SerializationException("This Alignment.Vertical type is not supported: $string")
        }
        return AlignmentProvider.Vertical(alignment)
    }

    override fun serialize(encoder: Encoder, value: AlignmentProvider.Vertical) {
        val string = when (val alignment = value()) {
            Alignment.Top -> Top
            Alignment.CenterVertically -> CenterVertically
            Alignment.Bottom -> Bottom
            else -> throw SerializationException("This Alignment.Vertical type is not supported: $alignment")
        }
        encoder.encodeString(string)
    }

    private companion object {
        const val Top = "Top"
        const val CenterVertically = "CenterVertically"
        const val Bottom = "Bottom"
    }
}
