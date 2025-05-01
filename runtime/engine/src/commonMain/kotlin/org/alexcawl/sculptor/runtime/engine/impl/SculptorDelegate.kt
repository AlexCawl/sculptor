package org.alexcawl.sculptor.runtime.engine.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.viewModelFactory
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.SculptorBuilder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class SculptorDelegate(
    private val globalDiTree: DiTree,
    private val builder: SculptorBuilder.() -> Unit,
) : ReadOnlyProperty<ViewModelStoreOwner, Sculptor> {
    override fun getValue(thisRef: ViewModelStoreOwner, property: KProperty<*>): Sculptor {
        val viewModel: DiTreeViewModel = thisRef.getViewModel factory@{
            val diTree = SculptorBuilderImpl(globalDiTree = globalDiTree).apply(builder).build()
            return@factory DiTreeViewModel(diTree = diTree)
        }
        return SculptorImpl(diTree = viewModel)
    }

    private inline fun <reified VM : ViewModel> ViewModelStoreOwner.getViewModel(
        crossinline factory: () -> VM,
    ): VM {
        return ViewModelLazy(
            viewModelClass = VM::class,
            storeProducer = { viewModelStore },
            factoryProducer = {
                viewModelFactory {
                    addInitializer(VM::class) { factory() }
                }
            }
        ).value
    }
}
