package org.alexcawl.sculptor.engine.impl.contentService

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alexcawl.sculptor.common.contract.SculptorContent
import org.alexcawl.sculptor.engine.api.contentService.ContentResolutionStrategy
import org.alexcawl.sculptor.engine.api.contentService.ContentService

internal class RouterContentService(
    override val contentResolutionStrategy: ContentResolutionStrategy,
    private val contentServices: List<ContentService>,
) : ContentService {
    override fun invoke(key: String): Flow<Result<SculptorContent>> = runCatching {
        contentServices.first { contentService: ContentService ->
            contentService.contentResolutionStrategy == contentResolutionStrategy
        }
    }.map { contentService: ContentService ->
        contentService.invoke(key = key)
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
