@file:Suppress("NAME_SHADOWING")

package org.alexcawl.sculptor.common.presenter

import org.alexcawl.sculptor.common.contract.Scaffold
import org.alexcawl.sculptor.common.contract.Section
import org.alexcawl.sculptor.common.core.InternalSculptorApi
import org.alexcawl.sculptor.common.core.Logger
import org.alexcawl.sculptor.common.core.Tag
import org.alexcawl.sculptor.common.layout.Layout
import kotlin.reflect.KClass

public data object ScaffoldPresenter : Presenter<Scaffold, Layout>() {
    override val input: KClass<Scaffold> = Scaffold::class
    override val output: KClass<Layout> = Layout::class

    @InternalSculptorApi
    override fun internalTransform(scope: PresenterScope, input: Any): Layout {
        val input: Scaffold = input as? Scaffold
            ?: Logger.e(
                tag = Tag.SCAFFOLD_PRESENTER,
                message = "Input is not a Scaffold. Expected ${this.input} but it was ${input::class} instead"
            )
        val section: Section = input.section
        return scope.internalMap(section::class, Layout::class, section) as Layout
    }
}
