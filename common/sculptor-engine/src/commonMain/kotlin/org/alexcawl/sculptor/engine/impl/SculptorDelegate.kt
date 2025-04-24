package org.alexcawl.sculptor.engine.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.viewModelFactory
import org.alexcawl.sculptor.common.di.DiTree
import org.alexcawl.sculptor.engine.Sculptor
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

public class SculptorDelegate(private val globalDiTree: Lazy<DiTree>) : ReadOnlyProperty<ViewModelStoreOwner, Sculptor> {
    override fun getValue(thisRef: ViewModelStoreOwner, property: KProperty<*>): Sculptor {
        TODO("Not yet implemented")
    }
}

private inline fun <reified VM : ViewModel> ViewModelStoreOwner.lazyViewModel(
    crossinline factory: () -> VM
): Lazy<VM> = ViewModelLazy(
    viewModelClass = VM::class,
    storeProducer = { viewModelStore },
    factoryProducer = {
        viewModelFactory {
            addInitializer(VM::class) { factory() }
        }
    }
)
