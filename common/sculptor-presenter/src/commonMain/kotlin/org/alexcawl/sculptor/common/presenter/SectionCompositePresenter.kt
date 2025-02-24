package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.contract.StateContract
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.core.Logger
import org.alexcawl.sculptor.common.core.Tag
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

public data object SectionCompositePresenter : Presenter<Section.Composite, Layout>() {
    override val input: KClass<Section.Composite> = Section.Composite::class
    override val output: KClass<Layout> = Layout::class

    @InternalSculptorApi
    override fun internalTransform(scope: PresenterScope, input: Any): Layout {
        val input: Section.Composite = input as? Section.Composite
            ?: Logger.e(
                tag = Tag.SECTION_COMPOSITE_PRESENTER,
                message = "Input is not a Block<*>. Expected ${this.input} but it was ${input::class} instead"
            )
        val state: StateContract = input.state
        val bundle = StatePresenter.Bundle(
            id = input.id + state.id,
            modifiers = scope.buildModifier(input.modifiers),
            state = state,
        )
        return scope.internalMap(state::class, Layout::class, bundle) as Layout    }
}
