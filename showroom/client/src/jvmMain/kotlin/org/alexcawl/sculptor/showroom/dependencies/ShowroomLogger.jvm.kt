package org.alexcawl.sculptor.showroom.dependencies

import org.alexcawl.sculptor.runtime.engine.dependencies.SculptorLogger

internal actual class ShowroomLogger : SculptorLogger {
    override fun debug(tag: String, message: String) {
        println("[D] $tag: $message")
    }

    override fun info(tag: String, message: String) {
        println("[I] $tag: $message")
    }

    override fun warning(tag: String, message: String) {
        println("[W] $tag: $message")
    }

    override fun error(tag: String, message: String) {
        println("[E] $tag: $message")
    }
}
