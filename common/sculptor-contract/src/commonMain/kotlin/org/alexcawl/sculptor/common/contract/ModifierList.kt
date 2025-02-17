package org.alexcawl.sculptor.common.contract

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.serializer

/**
 * TODO: docs
 */
@Serializable(with = ModifierListSerializer::class)
public sealed class ModifierList(
    public val modifiers: List<ModifierContract>
) : List<ModifierContract> by modifiers {
    /**
     * TODO: docs
     */
    public infix operator fun plus(modifier: ModifierContract): ModifierList = Impl(modifiers = modifiers + modifier)

    /**
     * TODO: docs
     */
    public companion object : ModifierList(emptyList())

    internal class Impl(modifiers: List<ModifierContract>) : ModifierList(modifiers = modifiers)
}

internal class ModifierListSerializer : KSerializer<ModifierList> {
    private val serializer: KSerializer<List<ModifierContract>> = serializer()
    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun deserialize(decoder: Decoder): ModifierList {
        return ModifierList.Impl(serializer.deserialize(decoder))
    }

    override fun serialize(encoder: Encoder, value: ModifierList) {
       serializer.serialize(encoder, value.modifiers)
    }
}
