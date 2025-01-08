package org.alexcawl.skulptor.core.attribute

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

typealias DpSerializable = @Serializable(with = DpSerializer::class) Dp

internal class DpSerializer : KSerializer<Dp> {
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor(
        serialName = "androidx.compose.ui.unit.Dp",
        original = Wrapper.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): Dp {
        return when (val wrapper: Wrapper = decoder.decodeSerializableValue(Wrapper.serializer())) {
            is Wrapper.Number -> wrapper.value.dp
            is Wrapper.Hairline -> Dp.Hairline
            is Wrapper.Infinity -> Dp.Infinity
            is Wrapper.Unspecified -> Dp.Unspecified
        }
    }

    override fun serialize(encoder: Encoder, value: Dp) {
        val wrapper: Wrapper = when (value) {
            Dp.Hairline -> Wrapper.Hairline
            Dp.Infinity -> Wrapper.Infinity
            Dp.Unspecified -> Wrapper.Unspecified
            else -> Wrapper.Number(value.value)
        }
        encoder.encodeSerializableValue(Wrapper.serializer(), wrapper)
    }

    @Serializable
    private sealed interface Wrapper {
        @Serializable
        @SerialName("dp@number")
        data class Number(val value: Float) : Wrapper

        @Serializable
        @SerialName("dp@hairline")
        data object Hairline : Wrapper

        @Serializable
        @SerialName("dp@infinity")
        data object Infinity : Wrapper

        @Serializable
        @SerialName("dp@unspecified")
        data object Unspecified : Wrapper
    }
}
