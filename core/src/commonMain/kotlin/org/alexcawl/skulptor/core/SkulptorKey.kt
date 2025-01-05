package org.alexcawl.skulptor.core

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = SkulptorKeySerializer::class)
data class SkulptorKey(
    val module: String,
    val type: String
)

private class SkulptorKeySerializer : KSerializer<SkulptorKey> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = CLASS_ID,
        kind = PrimitiveKind.STRING
    )

    override fun serialize(encoder: Encoder, value: SkulptorKey) {
        val string = "${value.module}@${value.type}"
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): SkulptorKey {
        val string = decoder.decodeString()
        val (module, type) = string.split("@")
        return SkulptorKey(module = module, type = type)
    }

    private companion object {
        const val CLASS_ID: String = "org.alexcawl.skulptor.core.SkulptorKey"
    }
}
