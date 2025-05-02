package org.alexcawl.sculptor.runtime.engine.dependencies.deeplink

public interface DeeplinkToUrlResolver {
    public suspend fun resolve(deeplink: String): Pair<String, String>
}
