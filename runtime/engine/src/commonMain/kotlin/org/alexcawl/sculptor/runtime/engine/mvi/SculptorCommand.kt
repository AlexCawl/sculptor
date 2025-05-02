package org.alexcawl.sculptor.runtime.engine.mvi

internal interface SculptorCommand {
    data class ResolveUrlFromDeeplinkCommand(val deeplink: String) : SculptorCommand

    data class LoadContentCommand(val key: String, val url: String) : SculptorCommand
}