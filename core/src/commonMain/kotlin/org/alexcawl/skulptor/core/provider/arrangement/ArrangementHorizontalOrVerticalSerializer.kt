package org.alexcawl.skulptor.core.provider.arrangement

import androidx.compose.foundation.layout.Arrangement
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.alexcawl.skulptor.core.provider.DpSerializable

class ArrangementHorizontalOrVerticalSerializer : KSerializer<Arrangement.HorizontalOrVertical> {
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor(
        serialName = "androidx.compose.foundation.layout.Arrangement.HorizontalOrVertical",
        original = Wrapper.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): Arrangement.HorizontalOrVertical {
        return when (val wrapper: Wrapper = decoder.decodeSerializableValue(Wrapper.serializer())) {
            is Wrapper.Center -> Arrangement.Center
            is Wrapper.SpaceAround -> Arrangement.SpaceAround
            is Wrapper.SpaceBetween -> Arrangement.SpaceBetween
            is Wrapper.SpaceEvenly -> Arrangement.SpaceEvenly
            is Wrapper.SpacedBy -> Arrangement.spacedBy(wrapper.space)
        }
    }

    override fun serialize(encoder: Encoder, value: Arrangement.HorizontalOrVertical) {
        val wrapper: Wrapper = when (value) {
            Arrangement.Center -> Wrapper.Center
            Arrangement.SpaceAround -> Wrapper.SpaceAround
            Arrangement.SpaceBetween -> Wrapper.SpaceBetween
            Arrangement.SpaceEvenly -> Wrapper.SpaceEvenly
            else -> error("")
        }
        encoder.encodeSerializableValue(Wrapper.serializer(), wrapper)
    }

    @Serializable
    private sealed interface Wrapper {
        @Serializable
        @SerialName("center")
        data object Center : Wrapper

        @Serializable
        @SerialName("space_evenly")
        data object SpaceEvenly : Wrapper

        @Serializable
        @SerialName("space_between")
        data object SpaceBetween : Wrapper

        @Serializable
        @SerialName("space_around")
        data object SpaceAround : Wrapper

        @Serializable
        @SerialName("spaced_by")
        data class SpacedBy(val space: DpSerializable) : Wrapper
    }
}