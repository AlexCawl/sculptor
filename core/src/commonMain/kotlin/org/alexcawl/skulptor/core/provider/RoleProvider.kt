@file:Suppress("ConstPropertyName")

package org.alexcawl.skulptor.core.provider

import androidx.compose.ui.semantics.Role
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.alexcawl.skulptor.core.Provider

@Serializable(with = RoleSerializer::class)
data class RoleProvider(private val role: Role) : Provider<Role> {
    override fun invoke(): Role = role
}

private class RoleSerializer : KSerializer<RoleProvider> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "androidx.compose.ui.semantics.Role",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder): RoleProvider {
        val role = when (val string = decoder.decodeString()) {
            Button -> Role.Button
            Checkbox -> Role.Checkbox
            DropdownList -> Role.DropdownList
            Image -> Role.Image
            RadioButton -> Role.RadioButton
            Switch -> Role.Switch
            Tab -> Role.Tab
            else -> throw SerializationException("This Role type is not supported: $string")
        }
        return RoleProvider(role)
    }

    override fun serialize(encoder: Encoder, value: RoleProvider) {
        val string = when (val role = value()) {
            Role.Button -> Button
            Role.Checkbox -> Checkbox
            Role.DropdownList -> DropdownList
            Role.Image -> Image
            Role.RadioButton -> RadioButton
            Role.Switch -> Switch
            Role.Tab -> Tab
            else -> throw SerializationException("This Role type is not supported: $role")
        }
        encoder.encodeString(string)
    }

    private companion object {
        const val Button = "Button"
        const val Checkbox = "Checkbox"
        const val Switch = "Switch"
        const val RadioButton = "RadioButton"
        const val Tab = "Tab"
        const val Image = "Image"
        const val DropdownList = "DropdownList"
    }
}
