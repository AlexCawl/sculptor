package org.alexcawl.sculptor.engine

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.layout.LayoutContract
import org.alexcawl.sculptor.common.contract.value.ValueContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.presenter.LayoutProvider
import org.alexcawl.sculptor.common.presenter.Presenter
import org.alexcawl.sculptor.common.presenter.PresenterProvider
import org.alexcawl.sculptor.common.presenter.PresenterScope
import org.alexcawl.sculptor.common.presenter.PresenterState
import org.alexcawl.sculptor.common.presenter.ValueProvider
import kotlin.reflect.KClass

sealed interface PresenterEngine {
    fun transform(scaffold: Scaffold): Layout

    fun findPresenter(inputClass: KClass<out Any>, outputClass: KClass<out Any>): Presenter<*, *>

    companion object Factory {
        fun create(state: PresenterState): PresenterEngine =
            PresenterEngineImpl(presenters = state.presenters)
    }
}

@OptIn(InternalSculptorApi::class)
private class PresenterEngineImpl(
    private val presenters: List<Presenter<*, *>>
) : PresenterEngine {
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

    private fun findLayout(scaffold: Scaffold, identifier: Identifier): LayoutContract =
        scaffold.layouts.find { it.id == identifier } ?: error("No layout found for $identifier")

    private fun findValue(scaffold: Scaffold, identifier: Identifier): ValueContract =
        scaffold.values.find { it.id == identifier }?: error("No value found for $identifier")
}
