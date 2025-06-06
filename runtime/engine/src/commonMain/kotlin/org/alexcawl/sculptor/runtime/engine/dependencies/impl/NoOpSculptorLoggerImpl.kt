package org.alexcawl.sculptor.runtime.engine.dependencies.impl

import org.alexcawl.sculptor.runtime.engine.dependencies.SculptorLogger

internal data object NoOpSculptorLoggerImpl : SculptorLogger {
    override fun debug(tag: String, message: String): Unit = Unit

    override fun info(tag: String, message: String): Unit = Unit

    override fun warning(tag: String, message: String): Unit = Unit

    override fun error(tag: String, message: String): Unit = Unit
}
