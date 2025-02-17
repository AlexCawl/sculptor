package org.alexcawl.sculptor.engine

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ValueContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.CommonPresenter
import org.alexcawl.sculptor.common.presenter.LayoutPresenter
import org.alexcawl.sculptor.common.presenter.LayoutProvider
import org.alexcawl.sculptor.common.presenter.ModifierPresenter
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterProvider
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.ValueProvider
import kotlin.reflect.KClass

/**
 * TODO: docs
 */
public sealed interface SculptorPresenter {
    /**
     * TODO: docs
     */
    public fun transform(scaffold: Scaffold): Layout

    /**
     * TODO: docs
     */
    public fun findPresenter(inputClass: KClass<out Any>, outputClass: KClass<out Any>): Presenter<*, *>

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
        public val layoutPresenters: List<LayoutPresenter<*, *>>

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
            get() = layoutPresenters + modifierPresenters + commonPresenters
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
    private val presenters: List<Presenter<*, *>>,
) : SculptorPresenter {
    override fun transform(scaffold: Scaffold): Layout {
        val presenterProvider: PresenterProvider = this::findPresenter
        val layoutProvider: LayoutProvider = { id: Identifier -> findLayout(scaffold, id) }
        val valueProvider: ValueProvider = { id: Identifier -> findValue(scaffold, id) }
        val presenterScope = PresenterScope(
            presenterProvider = presenterProvider,
            layoutProvider = layoutProvider,
            valueProvider = valueProvider,
        )
        val rootLayout: LayoutContract = findLayout(scaffold, scaffold.rootLayoutId)
        val rootLayoutPresenter: Presenter<*, *> = findPresenter(rootLayout::class, Layout::class)
        return rootLayoutPresenter.internalTransform(presenterScope, rootLayout) as Layout
    }

    override fun findPresenter(
        inputClass: KClass<out Any>,
        outputClass: KClass<out Any>
    ): Presenter<*, *> = presenters
        .firstOrNull { it.input == inputClass && it.output == outputClass }
        ?: error("No presenter found for $inputClass -> $outputClass")

    override fun plus(other: SculptorPresenter): SculptorPresenter = when (other) {
        is SculptorPresenterImpl -> SculptorPresenterImpl(presenters = presenters + other.presenters)
    }

    private fun findLayout(scaffold: Scaffold, identifier: Identifier): LayoutContract =
        scaffold.layouts.find { it.id == identifier } ?: error("No layout found for $identifier")

    private fun findValue(scaffold: Scaffold, identifier: Identifier): ValueContract =
        scaffold.values.find { it.id == identifier }?: error("No value found for $identifier")
}
