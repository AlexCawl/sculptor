package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.contract.SculptorContractor
import org.alexcawl.sculptor.common.core.ExperimentalSculptorApi
import org.alexcawl.sculptor.common.layout.SculptorRenderer
import org.alexcawl.sculptor.common.layout.SculptorScreen
import org.alexcawl.sculptor.common.presenter.SculptorPresenter

@Immutable
sealed interface Sculptor {
    fun launch(mode: LaunchMode): SculptorScreen

    operator fun plus(other: Sculptor): Sculptor

    companion object Factory {
        fun create(
            contractorState: SculptorContractor.State,
            presenterState: SculptorPresenter.State,
            rendererState: SculptorRenderer.State,
        ): Sculptor = Impl(
            sculptorContractor = SculptorContractor.create(contractorState),
            sculptorPresenter = SculptorPresenter.create(presenterState),
            rendererEngine = SculptorRenderer.create(rendererState)
        )

        @ExperimentalSculptorApi
        fun create(
            contractor: SculptorContractor,
            presenter: SculptorPresenter,
            renderer: SculptorRenderer,
        ): Sculptor = Impl(
            sculptorContractor = contractor,
            sculptorPresenter = presenter,
            rendererEngine = renderer,
        )
    }
}

@Immutable
private data class Impl(
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
        is Impl -> Impl(
            sculptorContractor = sculptorContractor + other.sculptorContractor,
            sculptorPresenter = sculptorPresenter + other.sculptorPresenter,
            rendererEngine = rendererEngine + other.rendererEngine,
        )
    }
}
