package org.alexcawl.sculptor.engine.template

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.SculptorScreen
import org.alexcawl.sculptor.common.contract.SculptorScreen.Scaffold
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.Section.Block
import org.alexcawl.sculptor.common.contract.Section.Template

internal class TemplateAssemblerImpl(private val stringFormat: StringFormat) : TemplateAssembler {
    override suspend fun assemble(schema: SculptorScreen.Schema): Scaffold {
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
                            val encodedTemplate: String =
                                stringFormat.encodeToString(value = resultTemplate)
                            stringFormat.decodeFromString<Block>(string = encodedTemplate)
                        }
                    }
                }
            }.awaitAll()
        }
        return Scaffold(
            blocks = blocks,
            root = root,
        )
    }
}
