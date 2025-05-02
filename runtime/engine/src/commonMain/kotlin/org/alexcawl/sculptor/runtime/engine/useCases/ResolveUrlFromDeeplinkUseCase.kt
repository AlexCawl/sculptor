package org.alexcawl.sculptor.runtime.engine.useCases

import org.alexcawl.sculptor.runtime.engine.dependencies.deeplink.DeeplinkToUrlResolver
import org.alexcawl.sculptor.runtime.engine.mvi.SculptorCommand.ResolveUrlFromDeeplinkCommand
import org.alexcawl.sculptor.runtime.engine.mvi.SculptorEvent
import org.alexcawl.sculptor.runtime.engine.mvi.SculptorUseCase
import kotlin.reflect.KClass

internal class ResolveUrlFromDeeplinkUseCase(
    private val deeplinkToUrlResolver: DeeplinkToUrlResolver,
) : SculptorUseCase<ResolveUrlFromDeeplinkCommand>() {
    override val key: KClass<ResolveUrlFromDeeplinkCommand> = ResolveUrlFromDeeplinkCommand::class

    override suspend fun TaskBuilder.handle(command: ResolveUrlFromDeeplinkCommand) {
        val (key: String, url: String) = deeplinkToUrlResolver.resolve(deeplink = command.deeplink)
        dispatch {
            SculptorEvent.LoadContentEvent(key = key, url = url)
        }
    }
}
