package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.core.ExperimentalSculptorApi

/**
 * TODO: docs
 */
@Immutable
public sealed interface Sculptor {
    /**
     * TODO: docs
     */
    public fun launch(mode: LaunchMode): SculptorScreen

    /**
     * TODO: docs
     */
    public operator fun plus(other: Sculptor): Sculptor

    /**
     * TODO: docs
     */
    public companion object Factory {
        /**
         * TODO: docs
         */
        public fun create(
            contractorState: SculptorContractor.State,
            presenterState: SculptorPresenter.State,
            rendererState: SculptorRenderer.State,
        ): Sculptor = SculptorImpl(
            sculptorContractor = SculptorContractor.create(contractorState),
            sculptorPresenter = SculptorPresenter.create(presenterState),
            rendererEngine = SculptorRenderer.create(rendererState)
        )

        /**
         * TODO: docs
         */
        @ExperimentalSculptorApi
        public fun create(
            contractor: SculptorContractor,
            presenter: SculptorPresenter,
            renderer: SculptorRenderer,
        ): Sculptor = SculptorImpl(
            sculptorContractor = contractor,
            sculptorPresenter = presenter,
            rendererEngine = renderer,
        )
    }
}

@Immutable
private data class SculptorImpl(
    private val sculptorContractor: SculptorContractor,
    private val sculptorPresenter: SculptorPresenter,
    private val rendererEngine: SculptorRenderer,
) : Sculptor {
    override fun launch(mode: LaunchMode): SculptorScreen = when (mode) {
        is LaunchMode.FromRaw -> mode.string
            .let(sculptorContractor::contract)
            .let(sculptorPresenter::transform)
            .let(rendererEngine::render)

        is LaunchMode.FromScaffold -> mode.scaffold
            .let(sculptorPresenter::transform)
            .let(rendererEngine::render)

        is LaunchMode.FromLayout -> mode.layout
            .let(rendererEngine::render)
    }

    override fun plus(other: Sculptor): Sculptor = when (other) {
        is SculptorImpl -> SculptorImpl(
            sculptorContractor = sculptorContractor + other.sculptorContractor,
            sculptorPresenter = sculptorPresenter + other.sculptorPresenter,
            rendererEngine = rendererEngine + other.rendererEngine,
        )
    }
}
