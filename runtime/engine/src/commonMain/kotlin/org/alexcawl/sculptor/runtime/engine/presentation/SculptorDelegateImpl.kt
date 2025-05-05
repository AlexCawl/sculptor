package org.alexcawl.sculptor.runtime.engine.presentation

import androidx.lifecycle.ViewModelStoreOwner
import org.alexcawl.sculptor.internal.di.DiTree
import org.alexcawl.sculptor.runtime.engine.Sculptor
import org.alexcawl.sculptor.runtime.engine.SculptorBuilder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class SculptorDelegateImpl(
    private val globalDiTree: DiTree,
    private val builder: SculptorBuilder.() -> Unit,
) : ReadOnlyProperty<ViewModelStoreOwner, Sculptor> {
    override fun getValue(thisRef: ViewModelStoreOwner, property: KProperty<*>): Sculptor {
        return SculptorImpl(
            viewModelStoreOwner = thisRef,
            diTreeBuilder = SculptorBuilderImpl(globalDiTree = globalDiTree).apply(builder)
        )
    }
}
