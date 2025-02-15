package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.contract.ContractorState
import org.alexcawl.sculptor.common.layout.RendererState
import org.alexcawl.sculptor.common.presenter.PresenterState

@Immutable
interface Sculptor : ContractorEngine, PresenterEngine, RendererEngine {
    fun launch(mode: LaunchMode): SculptorScreen

    companion object Factory {
        fun create(
            contractorState: ContractorState,
            presenterState: PresenterState,
            rendererState: RendererState,
        ): Sculptor = SculptorImpl(
            contractorEngine = ContractorEngine.create(contractorState),
            presenterEngine = PresenterEngine.create(presenterState),
            rendererEngine = RendererEngine.create(rendererState)
        )
    }
}

@Immutable
private data class SculptorImpl(
    private val contractorEngine: ContractorEngine,
    private val presenterEngine: PresenterEngine,
    private val rendererEngine: RendererEngine,
) : Sculptor,
    ContractorEngine by contractorEngine,
    PresenterEngine by presenterEngine,
    RendererEngine by rendererEngine {

    override fun launch(mode: LaunchMode): SculptorScreen = when (mode) {
        is LaunchMode.FromRaw -> mode.string
            .let(::contract)
            .let(::transform)
            .let(::render)

        is LaunchMode.FromScaffold -> mode.scaffold
            .let(::transform)
            .let(::render)

        is LaunchMode.FromLayout -> mode.layout
            .let(::render)
    }
}
