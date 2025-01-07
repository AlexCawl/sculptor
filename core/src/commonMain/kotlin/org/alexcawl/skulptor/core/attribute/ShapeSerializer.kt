package org.alexcawl.skulptor.core.attribute

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

typealias ShapeSerializable = @Serializable(with = ShapeSerializer::class) Shape

internal class ShapeSerializer : KSerializer<Shape> {
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor(
        serialName = "androidx.compose.ui.graphics.Shape",
        original = Wrapper.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): Shape {
        return when (val wrapper: Wrapper = decoder.decodeSerializableValue(Wrapper.serializer())) {
            is Wrapper.Circle -> CircleShape
            is Wrapper.Rectangle -> RectangleShape
            is Wrapper.RoundedCorner -> with(wrapper) {
                RoundedCornerShape(
                    topStart = topStart,
                    topEnd = topEnd,
                    bottomEnd = bottomEnd,
                    bottomStart = bottomStart
                )
            }
        }
    }

    override fun serialize(encoder: Encoder, value: Shape) {
        val wrapper = when (value) {
            CircleShape -> Wrapper.Circle
            RectangleShape -> Wrapper.Rectangle
            is RoundedCornerShape -> Wrapper.RoundedCorner(
                topStart = value.topStart,
                topEnd = value.topEnd,
                bottomEnd = value.bottomEnd,
                bottomStart = value.bottomStart,
            )
            else -> throw SerializationException("This Shape type is not supported: $value")
        }
        return encoder.encodeSerializableValue(Wrapper.serializer(), wrapper)
    }


    @Serializable
    private sealed interface Wrapper {
        @Serializable
        @SerialName("shape@rectangle")
        data object Rectangle : Wrapper

        @Serializable
        @SerialName("shape@circle")
        data object Circle : Wrapper

        @Serializable
        @SerialName("shape@rounded_corner")
        data class RoundedCorner(
            val topStart: CornerSizeSerializable,
            val topEnd: CornerSizeSerializable,
            val bottomEnd: CornerSizeSerializable,
            val bottomStart: CornerSizeSerializable
        ) : Wrapper
    }
}
