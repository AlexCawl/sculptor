package org.alexcawl.sculptor.runtime.engine.domain

import org.alexcawl.sculptor.core.contract.SculptorContent
import org.alexcawl.sculptor.runtime.engine.ui.SculptorIntent

internal interface SculptorCommand {
    data class TransformToRequestCommand(val intent: SculptorIntent) : SculptorCommand

    data class LoadContentCommand(val key: String, val url: String) : SculptorCommand

    data class DemarshallContentCommand(val rawContent: String) : SculptorCommand

    data class SaveContentToCacheCommand(val content: SculptorContent) : SculptorCommand

    data class TransformToLayoutCommand(val content: SculptorContent) : SculptorCommand
}
