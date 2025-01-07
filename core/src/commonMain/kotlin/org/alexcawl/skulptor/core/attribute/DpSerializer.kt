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
            is Wrapper.FromNumber.FromDouble -> wrapper.value.dp
            is Wrapper.FromNumber.FromFloat -> wrapper.value.dp
            is Wrapper.FromNumber.FromInt -> wrapper.value.dp
            Wrapper.Hairline -> Dp.Hairline
            Wrapper.Infinity -> Dp.Infinity
            Wrapper.Unspecified -> Dp.Unspecified
        }
    }

    override fun serialize(encoder: Encoder, value: Dp) {
        val wrapper: Wrapper = when (value) {
            Dp.Hairline -> Wrapper.Hairline
            Dp.Infinity -> Wrapper.Infinity
            Dp.Unspecified -> Wrapper.Unspecified
            else -> Wrapper.FromNumber.FromFloat(value.value)
        }
        encoder.encodeSerializableValue(Wrapper.serializer(), wrapper)
    }

    @Serializable
    private sealed interface Wrapper {
        @Serializable
        sealed interface FromNumber : Wrapper {
            val value: Number

            @Serializable
            @SerialName("dimension@dp_int")
            data class FromInt(override val value: Int) : FromNumber

            @Serializable
            @SerialName("dimension@dp_float")
            data class FromFloat(override val value: Float) : FromNumber

            @Serializable
            @SerialName("dimension@dp_double")
            data class FromDouble(override val value: Double) : FromNumber
        }

        @Serializable
        @SerialName("dimension@dp_hairline")
        data object Hairline : Wrapper

        @Serializable
        @SerialName("dimension@dp_infinity")
        data object Infinity : Wrapper

        @Serializable
        @SerialName("dimension@dp_unspecified")
        data object Unspecified : Wrapper
    }
}