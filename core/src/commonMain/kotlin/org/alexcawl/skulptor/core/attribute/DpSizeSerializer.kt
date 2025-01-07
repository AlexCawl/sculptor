package org.alexcawl.skulptor.core.attribute

import androidx.compose.ui.unit.DpSize
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

typealias DpSizeSerializable = @Serializable(with = DpSizeSerializer::class) DpSize

internal class DpSizeSerializer : KSerializer<DpSize> {
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor(
        serialName = "androidx.compose.ui.unit.DpSize",
        original = Wrapper.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): DpSize {
        val wrapper = decoder.decodeSerializableValue(Wrapper.serializer())
        return DpSize(
            width = wrapper.width,
            height = wrapper.height
        )
    }

    override fun serialize(encoder: Encoder, value: DpSize) {
        val wrapper = Wrapper(
            width = value.width,
            height = value.height
        )
        encoder.encodeSerializableValue(Wrapper.serializer(), wrapper)
    }

    @Serializable
    data class Wrapper(
        val width: DpSerializable,
        val height: DpSerializable,
    )
}
