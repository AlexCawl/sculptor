package org.alexcawl.sculptor.runtime.engine.dependencies.logger

public interface SculptorLogger {
    public fun debug(tag: String, message: String)

    public fun info(tag: String, message: String)

    public fun warning(tag: String, message: String)

    public fun error(tag: String, message: String)
}
