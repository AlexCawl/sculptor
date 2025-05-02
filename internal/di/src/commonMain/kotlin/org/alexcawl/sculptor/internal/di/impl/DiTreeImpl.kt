package org.alexcawl.sculptor.internal.di.impl

import org.alexcawl.sculptor.internal.di.DiComponent
import org.alexcawl.sculptor.internal.di.DiTree
import kotlin.reflect.KClass

internal class DiTreeImpl(override val diComponent: DiComponent) : DiTree {
    override fun <K : Any> get(key: KClass<K>): K = diComponent.get(key)

    override fun <T : Any> getAll(type: KClass<T>): List<T> = diComponent.getAll(type)

    override fun clone(): DiTree = DiTreeImpl(diComponent.clone())

    override fun close(): Unit = diComponent.close()
}
