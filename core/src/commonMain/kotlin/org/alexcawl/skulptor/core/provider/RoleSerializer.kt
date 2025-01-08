package org.alexcawl.skulptor.core.provider

import androidx.compose.ui.semantics.Role
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

typealias RoleSerializable = @Serializable(with = RoleSerializer::class) Role

internal class RoleSerializer : KSerializer<Role> {
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor(
        serialName = "androidx.compose.ui.semantics.Role",
        original = Wrapper.serializer().descriptor
    )

    override fun deserialize(decoder: Decoder): Role {
        return when (decoder.decodeSerializableValue(Wrapper.serializer())) {
            Wrapper.Button -> Role.Button
            Wrapper.Checkbox -> Role.Checkbox
            Wrapper.DropdownList -> Role.DropdownList
            Wrapper.Image -> Role.Image
            Wrapper.RadioButton -> Role.RadioButton
            Wrapper.Switch -> Role.Switch
            Wrapper.Tab -> Role.Tab
        }
    }

    override fun serialize(encoder: Encoder, value: Role) {
        val wrapper = when (value) {
            Role.Button -> Wrapper.Button
            Role.Checkbox -> Wrapper.Checkbox
            Role.DropdownList -> Wrapper.DropdownList
            Role.Image -> Wrapper.Image
            Role.RadioButton -> Wrapper.RadioButton
            Role.Switch -> Wrapper.Switch
            Role.Tab -> Wrapper.Tab
            else -> throw SerializationException("This Role type is not supported: $value")
        }
        encoder.encodeSerializableValue(Wrapper.serializer(), wrapper)
    }

    @Serializable
    private sealed interface Wrapper {
        @Serializable
        @SerialName("button")
        data object Button : Wrapper

        @Serializable
        @SerialName("checkbox")
        data object Checkbox : Wrapper

        @Serializable
        @SerialName("switch")
        data object Switch : Wrapper

        @Serializable
        @SerialName("radio_button")
        data object RadioButton : Wrapper

        @Serializable
        @SerialName("tab")
        data object Tab : Wrapper

        @Serializable
        @SerialName("image")
        data object Image : Wrapper

        @Serializable
        @SerialName("dropdown_list")
        data object DropdownList : Wrapper
    }
}
