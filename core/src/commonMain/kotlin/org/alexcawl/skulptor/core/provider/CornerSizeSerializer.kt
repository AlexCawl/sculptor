package org.alexcawl.skulptor.core.provider

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.unit.dp
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

typealias CornerSizeSerializable = @Serializable(with = CornerSizeSerializer::class) CornerSize

internal class CornerSizeSerializer : KSerializer<CornerSize> {
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor(
        serialName = "androidx.compose.foundation.shape.CornerSize",
        original = Wrapper.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): CornerSize {
        return when (val wrapper: Wrapper = decoder.decodeSerializableValue(Wrapper.serializer())) {
            is Wrapper.Dp -> CornerSize(wrapper.value)
            is Wrapper.Percent -> CornerSize(wrapper.value)
        }
    }

    override fun serialize(encoder: Encoder, value: CornerSize) {
        val string = value.toString()
        val dpValue: Wrapper.Dp? = parse(string = string, regex = dpCornerSizeRegex)
            .map { Wrapper.Dp(it.dp) }
            .getOrNull()
        if (dpValue != null) {
            encoder.encodeSerializableValue(Wrapper.serializer(), dpValue)
        } else {
            val percentValue = parse(string = string, regex = percentCornerSizeRegex)
                .map { Wrapper.Percent(it.toInt()) }
                .getOrNull()
            if (percentValue != null) {
                encoder.encodeSerializableValue(Wrapper.serializer(), percentValue)
            } else {
                throw SerializationException("This CornerSize type is not supported: $value")
            }
        }
    }

    private fun parse(string: String, regex: Regex): Result<Float> = runCatching {
        val match = regex.matchEntire(string)!!
        return@runCatching match.groupValues[1].toFloat()
    }

    @Serializable
    private sealed interface Wrapper {
        @Serializable
        @SerialName("dp")
        data class Dp(
            val value: DpSerializable
        ) : Wrapper

        @Serializable
        @SerialName("percent")
        data class Percent(
            val value: Int
        ) : Wrapper
    }

    private companion object {
        val dpCornerSizeRegex = "CornerSize\\(size = ([0-9.]+|NaN)\\.dp\\)".toRegex()
        val percentCornerSizeRegex = "CornerSize\\(size = ([0-9.]+)%\\)".toRegex()
    }
}
