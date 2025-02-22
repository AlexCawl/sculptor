package org.alexcawl.sculptor.common.core

public interface Logger {
    public fun w(tag: Tag = Tag.UNKNOWN, message: String)

    public fun e(tag: Tag = Tag.UNKNOWN, message: String): Nothing

    public companion object : Logger {
        override fun w(tag: Tag, message: String) {
            println("${tag.name}: $message")
        }

        override fun e(tag: Tag, message: String): Nothing {
            error("${tag.name}: $message")
        }
    }
}
