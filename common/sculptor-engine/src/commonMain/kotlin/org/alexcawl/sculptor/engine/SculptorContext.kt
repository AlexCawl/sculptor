@file:Suppress("UNCHECKED_CAST")

package org.alexcawl.sculptor.engine

import androidx.compose.runtime.Immutable
import org.alexcawl.sculptor.common.layout.Layout
import org.alexcawl.sculptor.common.layout.Renderer
import org.alexcawl.sculptor.common.presenter.Presenter
import kotlin.reflect.KClass

@Immutable
internal data class SculptorContext(
    val presenters: List<Presenter<*, *>>,
    val renderers: List<Renderer<*>>,
) {
    fun findPresenter(inputClass: KClass<out Any>, outputClass: KClass<out Any>): Presenter<*, *> {
        return presenters.firstOrNull {
            it.input == inputClass && it.output == outputClass
        } ?: error("No presenter found for $inputClass -> $outputClass")
    }

    inline fun <reified In : Any, reified Out : Any> findPresenter(): Presenter<In, Out> {
        return findPresenter(
            In::class as KClass<Any>,
            Out::class as KClass<Any>
        ) as Presenter<In, Out>
    }

    fun findRenderer(inputClass: KClass<out Layout>): Renderer<*> {
        return renderers.firstOrNull {
            it.layout == inputClass
        } ?: error("No renderer found for $inputClass")
    }

    inline fun <reified In : Layout> findRenderer(): Renderer<In> {
        return findRenderer(In::class as KClass<Layout>) as Renderer<In>
    }
}
