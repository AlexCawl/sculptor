package org.alexcawl.sculptor.showroom.dependencies

import org.alexcawl.sculptor.runtime.engine.dependencies.RemoteContentSource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import sculptor.showroom.client.generated.resources.Res

internal class ShowroomRemoteContentSource : RemoteContentSource {
    override suspend fun fetch(url: String): Result<String> = runCatching {
        @OptIn(ExperimentalResourceApi::class)
        Res.readBytes(url).decodeToString()
    }
}
