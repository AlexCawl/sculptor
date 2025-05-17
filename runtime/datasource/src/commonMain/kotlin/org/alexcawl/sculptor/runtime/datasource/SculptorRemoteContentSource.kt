package org.alexcawl.sculptor.runtime.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.alexcawl.sculptor.runtime.engine.dependencies.RemoteContentSource

public class SculptorRemoteContentSource(private val client: HttpClient) : RemoteContentSource {
    override suspend fun fetch(url: String): Result<String> {
        return client.get(urlString = url).body()
    }
}
