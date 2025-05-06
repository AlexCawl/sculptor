package org.alexcawl.sculptor.runtime.engine.domain.reducers

import org.alexcawl.sculptor.core.contract.ScreenScaffold
import org.alexcawl.sculptor.runtime.engine.domain.SculptorCommand
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent.HandleScaffoldEvent
import kotlin.reflect.KClass

internal class HandleScaffoldReducer : SculptorReducer<HandleScaffoldEvent>() {
    override val key: KClass<HandleScaffoldEvent> = HandleScaffoldEvent::class

    override suspend fun NextBuilder.reduce(event: HandleScaffoldEvent) {
        val (key: String, scaffold: ScreenScaffold) = event
        commands(
            SculptorCommand.SaveToCacheCommand(key = key, scaffold = scaffold),
            SculptorCommand.TransformToLayoutCommand(scaffold = scaffold),
        )
    }
}
