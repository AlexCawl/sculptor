package org.alexcawl.sculptor.runtime.engine.domain

import org.alexcawl.sculptor.core.contract.ScreenScaffold
import org.alexcawl.sculptor.runtime.engine.SculptorIntent

internal interface SculptorCommand {
    data class TransformToRequestCommand(val intent: SculptorIntent) : SculptorCommand

    data class LoadContentCommand(val key: String, val url: String) : SculptorCommand

    data class DemarshallContentCommand(val key: String, val rawContent: String) : SculptorCommand

    data class SaveToCacheCommand(val key: String, val scaffold: ScreenScaffold) : SculptorCommand

    data class TransformToLayoutCommand(val scaffold: ScreenScaffold) : SculptorCommand
}
