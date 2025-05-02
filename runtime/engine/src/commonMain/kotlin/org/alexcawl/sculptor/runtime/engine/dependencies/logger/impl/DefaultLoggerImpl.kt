package org.alexcawl.sculptor.runtime.engine.dependencies.logger.impl

import org.alexcawl.sculptor.runtime.engine.dependencies.logger.Logger

internal class DefaultLoggerImpl : Logger {
    override fun debug(tag: String, message: String) {
        println("[$DEBUG_TAG] $tag: $message")
    }

    override fun info(tag: String, message: String) {
        println("[$INFO_TAG] $tag: $message")
    }

    override fun warning(tag: String, message: String) {
        println("[$WARNING_TAG] $tag: $message")
    }

    override fun error(tag: String, message: String) {
        println("[$ERROR_TAG] $tag: $message")
    }

    private companion object {
        private const val DEBUG_TAG = "DEBUG"
        private const val INFO_TAG = "INFO"
        private const val WARNING_TAG = "WARNING"
        private const val ERROR_TAG = "ERROR"
    }
}
