package org.alexcawl.sculptor.foundation.contract.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = TextUnitSerializer::class)
public data class TextUnit(
    @SerialName("value")
    val value: Long,
) {
    public companion object {
        public val unspecified: TextUnit = TextUnit(value = -1)
    }
}

public val Number.sp: TextUnit
    get() = TextUnit(value = this.toLong())

internal class TextUnitSerializer : KSerializer<TextUnit> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "androidx.compose.ui.unit.TextUnit",
        kind = PrimitiveKind.STRING,
    )

    override fun deserialize(decoder: Decoder): TextUnit {
        return when (val string = decoder.decodeString()) {
            "unspecified" -> TextUnit.unspecified
            else -> TextUnit(value = string.toLong())
        }
    }

    override fun serialize(encoder: Encoder, value: TextUnit) {
        when (value) {
            TextUnit.unspecified -> encoder.encodeString("unspecified")
            else -> encoder.encodeString(value.value.toString())
        }
    }
}
