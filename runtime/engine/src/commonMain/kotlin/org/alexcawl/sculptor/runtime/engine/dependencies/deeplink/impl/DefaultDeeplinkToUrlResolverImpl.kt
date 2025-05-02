package org.alexcawl.sculptor.runtime.engine.dependencies.deeplink.impl

import org.alexcawl.sculptor.runtime.engine.dependencies.deeplink.DeeplinkToUrlResolver

public class DefaultDeeplinkToUrlResolverImpl : DeeplinkToUrlResolver {
    override suspend fun resolve(deeplink: String): Pair<String, String> {
        return deeplink to deeplink
    }
}
