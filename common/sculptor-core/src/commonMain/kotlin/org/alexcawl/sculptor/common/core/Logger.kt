package org.alexcawl.sculptor.common.core

import kotlin.jvm.Throws

public interface Logger {
    public fun w(tag: Tag = Tag.UNKNOWN, message: String)

    public fun e(tag: Tag = Tag.UNKNOWN, message: String): Nothing

    public companion object : Logger {
        override fun w(tag: Tag, message: String) {
            println("${tag.name}: $message")
        }

        @Throws(IllegalStateException::class)
        override fun e(tag: Tag, message: String): Nothing {
            error("${tag.name}: $message")
        }
    }
}
