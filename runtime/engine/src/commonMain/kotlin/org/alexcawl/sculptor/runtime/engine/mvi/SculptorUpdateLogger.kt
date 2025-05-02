package org.alexcawl.sculptor.runtime.engine.mvi

import org.alexcawl.sculptor.internal.mvi.logging.UpdateLogger
import org.alexcawl.sculptor.runtime.engine.dependencies.logger.Logger

internal class SculptorUpdateLogger(private val logger: Logger) : UpdateLogger {
    override fun log(tag: String, message: String): Unit = logger.debug(tag = tag, message = message)
}
