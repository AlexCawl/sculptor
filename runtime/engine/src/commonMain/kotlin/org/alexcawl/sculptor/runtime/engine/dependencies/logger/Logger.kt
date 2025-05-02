package org.alexcawl.sculptor.runtime.engine.dependencies.logger

public interface Logger {
    public fun debug(tag: String, message: String)

    public fun info(tag: String, message: String)

    public fun warning(tag: String, message: String)

    public fun error(tag: String, message: String)

    public companion object NoOp : Logger {
        override fun debug(tag: String, message: String): Unit = Unit

        override fun info(tag: String, message: String): Unit = Unit

        override fun warning(tag: String, message: String): Unit = Unit

        override fun error(tag: String, message: String) : Unit = Unit
    }
}
