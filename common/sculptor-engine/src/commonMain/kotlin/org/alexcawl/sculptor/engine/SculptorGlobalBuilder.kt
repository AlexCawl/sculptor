package org.alexcawl.sculptor.engine

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.renderer.Renderer
import org.alexcawl.sculptor.engine.api.ExceptionHandler
import org.alexcawl.sculptor.engine.api.contentService.ContentService
import org.alexcawl.sculptor.engine.api.contentService.LocalContentSource
import org.alexcawl.sculptor.engine.api.contentService.RemoteContentSource

public interface SculptorGlobalBuilder {
    public fun contentService(contentServiceProvider: () -> ContentService)

    public fun localContentSource(localContentSource: () -> LocalContentSource)

    public fun remoteContentSource(remoteContentSource: () -> RemoteContentSource)

    public fun exceptionHandler(exceptionHandler: () -> ExceptionHandler)

    public fun renderer(renderer: () -> Renderer<UiState>)

    public fun presenter(presenter: () -> Presenter<*, *>)

    public fun serializer(serializer: () -> KSerializer<*>)

    public fun feature(feature: Feature): Unit = with(feature) { install() }

    public interface Feature {
        public fun SculptorGlobalBuilder.install()
    }
}
