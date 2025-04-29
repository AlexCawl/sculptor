package org.alexcawl.sculptor.internal.di

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


private class DiTreeImpl(override val diComponent: DiComponent) : DiTree {
    override fun <K : Any> get(key: KClass<K>): K = diComponent.get(key)

    override fun <T : Any> getAll(type: KClass<T>): List<T> = diComponent.getAll(type)

    override fun clone(): DiTree = DiTreeImpl(diComponent.clone())

    override fun close(): Unit = diComponent.close()
}
