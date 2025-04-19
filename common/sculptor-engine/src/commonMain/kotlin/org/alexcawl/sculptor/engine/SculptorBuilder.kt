package org.alexcawl.sculptor.engine

import kotlinx.serialization.KSerializer
import org.alexcawl.sculptor.common.layout.UiState
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.renderer.Renderer

public interface SculptorBuilder {
    public fun renderer(renderer: () -> Renderer<UiState>)

    public fun presenter(presenter: () -> Presenter<*, *>)

    public fun serializer(serializer: () -> KSerializer<*>)

    public fun feature(feature: Feature): Unit = with(feature) { install() }

    public interface Feature {
        public fun SculptorBuilder.install()
    }
}
