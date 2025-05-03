package org.alexcawl.sculptor.showroom.dependencies

import android.content.Context
import org.alexcawl.sculptor.runtime.engine.dependencies.dataSource.RemoteContentSource
import org.alexcawl.sculptor.showroom.R

internal class ShowroomRemoteContentSource(
    private val context: Context,
) : RemoteContentSource {
    override suspend fun fetch(url: String): Result<String> {
        return Result.success(
            value = context.resources.openRawResource(R.raw.screen)
                .bufferedReader()
                .readText()
        )
    }
}
