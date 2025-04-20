package org.alexcawl.sculptor.engine.impl.contentService

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.sculptor.common.contract.SculptorContent
import org.alexcawl.sculptor.engine.api.contentService.ContentResolutionStrategy
import org.alexcawl.sculptor.engine.api.contentService.ContentResolver

internal class ContentService(
    private val contentResolutionStrategy: ContentResolutionStrategy,
    private val contentResolvers: List<ContentResolver>,
) {
    fun resolve(key: String): Flow<Result<SculptorContent>> = runCatching {
        contentResolvers.first { contentResolver: ContentResolver ->
            contentResolver.contentResolutionStrategy == contentResolutionStrategy
        }
    }.map { contentResolver: ContentResolver ->
        contentResolver.resolve(key = key)
    }.getOrElse { exception: Throwable ->
        flow {
            emit(
                Result.failure(
                    ContentNotFoundException(
                        message = "ContentService for $contentResolutionStrategy not found",
                        cause = exception,
                    )
                )
            )
        }
    }
}
