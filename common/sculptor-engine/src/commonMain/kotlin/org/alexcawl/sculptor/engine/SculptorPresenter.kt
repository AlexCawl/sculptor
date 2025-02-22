package org.alexcawl.sculptor.engine

import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.ModifierPresenter
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.SectionPresenter
import org.alexcawl.sculptor.common.presenter.StatePresenter

/**
 * TODO: docs
 */
public sealed interface SculptorPresenter {
    /**
     * TODO: docs
     */
    public val presenters: List<Presenter<*, *>>

    /**
     * TODO: docs
     */
    public fun transform(scaffold: Scaffold): Result<Layout>

    /**
     * TODO: docs
     */
    public operator fun plus(other: SculptorPresenter): SculptorPresenter

    /**
     * TODO: docs
     */
    public interface State {
        /**
         * TODO: docs
         */
        public val statePresenters: List<StatePresenter<*>>

        /**
         * TODO: docs
         */
        public val modifierPresenters: List<ModifierPresenter<*>>

        /**
         * TODO: docs
         */
        public val commonPresenters: List<CommonPresenter<*, *>>

        /**
         * TODO: docs
         */
        public val presenters: List<Presenter<*, *>>
            get() = statePresenters + modifierPresenters + commonPresenters + SectionPresenter
    }

    /**
     * TODO: docs
     */
    public companion object Factory {
        /**
         * TODO: docs
         */
        public fun create(state: State): SculptorPresenter =
            SculptorPresenterImpl(presenters = state.presenters)
    }
}

@OptIn(InternalSculptorApi::class)
private class SculptorPresenterImpl(
    override val presenters: List<Presenter<*, *>>,
) : SculptorPresenter {
    override fun transform(scaffold: Scaffold): Result<Layout> = runCatching {
        val presenterScope = PresenterScope(
            presenters = presenters,
            sections = scaffold.sections,
            values = scaffold.values,
        )
        val section: Section = scaffold.section
        presenterScope.internalMap(section::class, Layout::class, section) as Layout
    }

    override fun plus(other: SculptorPresenter): SculptorPresenter = SculptorPresenterImpl(
        presenters = presenters + other.presenters
    )
}
