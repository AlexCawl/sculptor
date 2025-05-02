package org.alexcawl.sculptor.internal.di

import org.alexcawl.sculptor.internal.di.impl.DiTreeImpl
import kotlin.reflect.KClass

public interface DiTree : AutoCloseable {
    public val diComponent: DiComponent

    public fun clone(): DiTree

    public fun <K : Any> get(key: KClass<K>): K

    public fun <T : Any> getAll(type: KClass<T>): List<T>

    public companion object {
        public operator fun invoke(diComponent: DiComponent): DiTree = DiTreeImpl(diComponent)
    }
}

public inline fun <reified T : Any> DiTree.get(): T = get(T::class)

public inline fun <reified T : Any> DiTree.getAll(): List<T> = getAll(T::class)
