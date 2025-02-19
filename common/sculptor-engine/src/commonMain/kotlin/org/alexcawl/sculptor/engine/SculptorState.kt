package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.core.ExperimentalSculptorApi

/**
 * TODO: docs
 */
@Immutable
public sealed interface SculptorState : SculptorContractor, SculptorPresenter, SculptorRenderer {
    /**
     * TODO: docs
     */
    public fun launch(mode: LaunchMode): Result<SculptorScreen>

    /**
     * TODO: docs
     */
    public operator fun plus(other: SculptorState): SculptorState

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
        ): SculptorState = SculptorStateImpl(
            sculptorContractor = SculptorContractor.create(contractorState),
            sculptorPresenter = SculptorPresenter.create(presenterState),
            sculptorRenderer = SculptorRenderer.create(rendererState)
        )

        /**
         * TODO: docs
         */
        @ExperimentalSculptorApi
        public fun create(
            contractor: SculptorContractor,
            presenter: SculptorPresenter,
            renderer: SculptorRenderer,
        ): SculptorState = SculptorStateImpl(
            sculptorContractor = contractor,
            sculptorPresenter = presenter,
            sculptorRenderer = renderer,
        )
    }
}

@Immutable
private data class SculptorStateImpl(
    private val sculptorContractor: SculptorContractor,
    private val sculptorPresenter: SculptorPresenter,
    private val sculptorRenderer: SculptorRenderer,
) : SculptorState,
    SculptorContractor by sculptorContractor,
    SculptorPresenter by sculptorPresenter,
    SculptorRenderer by sculptorRenderer {
    override fun launch(mode: LaunchMode): Result<SculptorScreen> = when (mode) {
        is LaunchMode.Data -> mode.string
            .let(sculptorContractor::decode)
            .map(sculptorPresenter::transform)
            .mapCatching {
                val canBeDrawn: Boolean = sculptorRenderer.measure(it).getOrThrow()
                when (canBeDrawn) {
                    true -> sculptorRenderer.draw(it)
                    false -> error("Cannot draw $it")
                }
            }

        is LaunchMode.Domain -> mode.scaffold
            .let(sculptorPresenter::transform)
            .mapCatching {
                val canBeDrawn: Boolean = sculptorRenderer.measure(it).getOrThrow()
                when (canBeDrawn) {
                    true -> sculptorRenderer.draw(it)
                    false -> error("Cannot draw $it")
                }
            }

        is LaunchMode.Ui -> mode.layout.let {
            runCatching {
                val canBeDrawn: Boolean = sculptorRenderer.measure(it).getOrThrow()
                when (canBeDrawn) {
                    true -> sculptorRenderer.draw(it)
                    false -> error("Cannot draw $it")
                }
            }
        }
    }

    override fun plus(other: SculptorState): SculptorState = when (other) {
        is SculptorStateImpl -> SculptorStateImpl(
            sculptorContractor = sculptorContractor + other.sculptorContractor,
            sculptorPresenter = sculptorPresenter + other.sculptorPresenter,
            sculptorRenderer = sculptorRenderer + other.sculptorRenderer,
        )
    }

    private inline fun <R, T> Result<T>.map(
        transform: (value: T) -> Result<R>
    ): Result<R> = runCatching {
        val value = this.getOrThrow()
        val transformed = transform(value).getOrThrow()
        transformed
    }
}
