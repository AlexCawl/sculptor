package org.alexcawl.sculptor.common.contract

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

public interface Constructor {
    public suspend fun construct(schema: SculptorScreen.Schema): SculptorScreen.Scaffold

    public companion object {
        public operator fun invoke(stringFormat: StringFormat): Constructor =
            ConstructorImpl(stringFormat = stringFormat)
    }
}

private class ConstructorImpl(private val stringFormat: StringFormat) : Constructor {
    override suspend fun construct(schema: SculptorScreen.Schema): SculptorScreen.Scaffold {
        val root: Identifier = schema.root
        val templates: List<Template> = schema.templates
        val blocks: List<Block> = coroutineScope {
            schema.sections.map { section: Section ->
                async(start = CoroutineStart.LAZY) {
                    when (section) {
                        is Block -> section
                        is Template -> {
                            val resultTemplate: Template = templates
                                .find { it.id == section.id }
                                ?.let { section + it }
                                ?: section
                            val encodedTemplate: String = stringFormat.encodeToString(value = resultTemplate)
                            stringFormat.decodeFromString<Block>(string = encodedTemplate)
                        }
                    }
                }
            }.awaitAll()
        }
        return SculptorScreen.Scaffold(
            blocks = blocks,
            root = root,
        )
    }
}
