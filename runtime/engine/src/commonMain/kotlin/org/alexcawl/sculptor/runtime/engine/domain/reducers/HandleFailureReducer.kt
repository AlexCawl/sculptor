package org.alexcawl.sculptor.runtime.engine.domain.reducers

import org.alexcawl.sculptor.runtime.engine.dependencies.logger.SculptorLogger
import org.alexcawl.sculptor.runtime.engine.domain.SculptorEvent.HandleFailureEvent
import org.alexcawl.sculptor.runtime.engine.ui.SculptorState
import kotlin.reflect.KClass

internal class HandleFailureReducer(
    private val sculptorLogger: SculptorLogger
) : SculptorReducer<HandleFailureEvent>() {
    override val key: KClass<HandleFailureEvent> = HandleFailureEvent::class

    override suspend fun NextBuilder.reduce(event: HandleFailureEvent) {
        when (event.type) {
            HandleFailureEvent.Type.UNIMPORTANT -> {
                sculptorLogger.warning(
                    tag = TAG,
                    message = event.cause.stackTraceToString(),
                )
            }
            HandleFailureEvent.Type.CRITICAL -> {
                sculptorLogger.error(
                    tag = TAG,
                    message = event.cause.stackTraceToString(),
                )
            }
        }
        state {
            SculptorState.Error
        }
    }

    private companion object {
        private const val TAG = "FailureReducer"
    }
}
