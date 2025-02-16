package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.Identifier
import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.LayoutContract
import org.alexcawl.sculptor.common.contract.ValueContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

sealed interface SculptorPresenter {
    fun transform(scaffold: Scaffold): Layout

    fun findPresenter(inputClass: KClass<out Any>, outputClass: KClass<out Any>): Presenter<*, *>

    operator fun plus(other: SculptorPresenter): SculptorPresenter

    interface State {
        val layoutPresenters: List<LayoutPresenter<*, *>>
        val modifierPresenters: List<ModifierPresenter<*>>
        val commonPresenters: List<CommonPresenter<*, *>>

        val presenters: List<Presenter<*, *>>
            get() = layoutPresenters + modifierPresenters + commonPresenters
    }

    companion object Factory {
        fun create(state: State): SculptorPresenter =
            Impl(presenters = state.presenters)
    }
}

@OptIn(InternalSculptorApi::class)
private class Impl(private val presenters: List<Presenter<*, *>>) : SculptorPresenter {
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
        is Impl -> Impl(presenters = presenters + other.presenters)
    }

    private fun findLayout(scaffold: Scaffold, identifier: Identifier): LayoutContract =
        scaffold.layouts.find { it.id == identifier } ?: error("No layout found for $identifier")

    private fun findValue(scaffold: Scaffold, identifier: Identifier): ValueContract =
        scaffold.values.find { it.id == identifier }?: error("No value found for $identifier")
}
